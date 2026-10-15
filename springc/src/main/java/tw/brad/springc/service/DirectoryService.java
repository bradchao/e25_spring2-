package tw.brad.springc.service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.brad.springc.dto.response.TableResult;
import tw.brad.springc.repository.CustomerRepository;
import tw.brad.springc.repository.EmployeeRepository;

/** 查姓名／公司名稱取得代碼，供模型接著查詢訂單。 */
@Service
public class DirectoryService {
    @Autowired
    private EmployeeRepository employees;
    @Autowired
    private CustomerRepository customers;

    public TableResult employees(String keyword) {
        var results = employees.search(toPattern(keyword));
        // Arrays.asList 允許 null，例如員工沒有主管時保留缺值。
        var rows = results.stream()
                .map(employee -> Arrays.<Object>asList(
                        employee.getEmployeeId(),
                        employee.getFirstName() + " " + employee.getLastName(),
                        employee.getTitle(),
                        employee.getCountry(),
                        employee.getReportsTo()))
                .toList();
        return new TableResult(
                List.of("員工編號", "姓名", "職稱", "國家", "主管編號"),
                rows,
                "符合姓名條件的全部員工；不含薪資與私人聯絡資料。");
    }

    public TableResult customers(String keyword) {
        var results = customers.search(toPattern(keyword));
        var rows = results.stream()
                .map(customer -> Arrays.<Object>asList(
                        customer.getCustomerId(),
                        customer.getCompanyName(),
                        customer.getCountry(),
                        customer.getCity()))
                .toList();
        return new TableResult(
                List.of("客戶代碼", "公司名稱", "國家", "城市"),
                rows,
                "符合公司名稱條件的全部客戶；不含電話、地址與聯絡人。");
    }

    /** 只轉換 LIKE 格式，不驗證資料；員工與客戶共用相同的關鍵字處理。 */
    static String toPattern(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return "%";
        }
        // % 與 _ 保留 LIKE 萬用字元意義，實際值由 Repository 綁定。
        return "%" + keyword.trim().toLowerCase() + "%";
    }
}
