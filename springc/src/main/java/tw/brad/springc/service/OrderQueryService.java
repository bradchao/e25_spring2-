package tw.brad.springc.service;

import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.brad.springc.dto.response.TableResult;
import tw.brad.springc.entity.Customer;
import tw.brad.springc.entity.Employee;
import tw.brad.springc.entity.Order;
import tw.brad.springc.repository.CustomerRepository;
import tw.brad.springc.repository.EmployeeRepository;
import tw.brad.springc.repository.OrderRepository;

/** 依工具傳入的 ID 查詢，並轉成前端可呈現的欄位。 */
@Service
@Transactional(readOnly = true, timeout = 5)
public class OrderQueryService {
    @Autowired
    private OrderRepository orders;

    @Autowired
    private EmployeeRepository employees;
    @Autowired
    private CustomerRepository customers;

    //public OrderQueryService(OrderRepository orders) {
    //    this.orders = orders;
    //}

    /** 員工與客戶查詢分開；直接綁定 ID，不另做參數檢查。 */
    public TableResult searchByEmployee(Integer employeeId) {
        var results = orders.findByEmployee(employeeId);
        return toTable(results, "員工編號 " + employeeId);
    }

    /** 客戶代碼保留原大小寫與尾端空白，例如教材中的 "Val2 "。 */
    public TableResult searchByCustomer(String customerId) {
        var results = orders.findByCustomer(customerId);
        return toTable(results, "客戶代碼 " + customerId);
    }

    /**
     * 一次 JOIN FETCH 取得姓名符合的員工與訂單。
     * 多人符合時只回傳候選員工，不混合他們的訂單，也不擅自挑選第一位。
     */
    public TableResult searchByEmployeeName(String keyword) {
        List<Employee> matches = employees.searchWithOrders(DirectoryService.toPattern(keyword));
        if (matches.isEmpty()) {
            return new TableResult(List.of("員工編號", "姓名"), List.of(), "找不到符合姓名的員工。");
        }
        if (matches.size() > 1) {
            var candidates = matches.stream()
                    .map(employee -> Arrays.<Object>asList(
                            employee.getEmployeeId(),
                            employee.getFirstName() + " " + employee.getLastName()))
                    .toList();
            return new TableResult(List.of("員工編號", "姓名"), candidates,
                    "需要澄清：符合多位員工。這是候選名單，不是訂單；請使用者指定員工編號，不可自行選擇。");
        }
        Employee employee = matches.get(0);
        return toTable(employee.getOrders(), "員工 " + employee.getFirstName() + " "
                + employee.getLastName() + "（編號 " + employee.getEmployeeId() + "）");
    }

    /** 公司名稱一次查詢；沒有訂單的公司仍會保留，與找不到公司區分。 */
    public TableResult searchByCustomerName(String keyword) {
        List<Customer> matches = customers.searchWithOrders(DirectoryService.toPattern(keyword));
        if (matches.isEmpty()) {
            return new TableResult(List.of("客戶代碼", "公司名稱"), List.of(), "找不到符合名稱的客戶。");
        }
        if (matches.size() > 1) {
            var candidates = matches.stream()
                    .map(customer -> Arrays.<Object>asList(customer.getCustomerId(), customer.getCompanyName()))
                    .toList();
            return new TableResult(List.of("客戶代碼", "公司名稱"), candidates,
                    "需要澄清：符合多家公司。這是候選名單，不是訂單；請使用者指定客戶代碼，不可自行選擇。");
        }
        Customer customer = matches.get(0);
        return toTable(customer.getOrders(), "客戶 " + customer.getCompanyName()
                + "（代碼 " + customer.getCustomerId() + "）");
    }

    /** ID 與名稱查詢共用結果排版，查詢條件本身仍各自獨立。 */
    private TableResult toTable(List<Order> results, String condition) {
        // JOIN FETCH 不保證集合內排序；在轉 DTO 時統一依訂單編號降冪，不修改 Entity 集合。
        var rows = results.stream()
                .sorted(Comparator.comparing(Order::getOrderId).reversed())
                .map(order -> Arrays.<Object>asList(
                        order.getOrderId(),
                        order.getCustomerId(),
                        order.getEmployeeId(),
                        order.getOrderDate() == null ? null : order.getOrderDate().toString(),
                        order.getShippedDate() == null ? null : order.getShippedDate().toString(),
                        order.getFreight()))
                .toList();

        return new TableResult(
                List.of("訂單編號", "客戶代碼", "員工編號", "訂購日期", "出貨日期", "運費"),
                rows,
                condition + "；依訂單編號遞減，回傳此條件的全部訂單。結果為空表示此主體沒有符合訂單；運費無幣別。");
    }
}
