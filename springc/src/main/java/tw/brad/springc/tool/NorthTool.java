package tw.brad.springc.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import tw.brad.springc.dto.response.TableResult;
import tw.brad.springc.dto.response.ToolTrace;
import tw.brad.springc.service.DirectoryService;
import tw.brad.springc.service.OrderQueryService;

import java.util.LinkedHashMap;
import java.util.List;

public class NorthTool {
    private final DirectoryService directory;
    private final OrderQueryService orders;
    private final List<ToolTrace> traces;

    public NorthTool(DirectoryService directory,
                     OrderQueryService orders,
                     List<ToolTrace> traces){
        this.directory = directory;
        this.orders = orders;
        this.traces = traces;
    }

    @Tool(description = "依英文姓名片段查全部符合的員工編號、姓名、職稱與主管編號；本工具只查基本資料。")
    public TableResult searchEmployees(
            @ToolParam(description = "英文姓名片段, 查全部的話為 null", required = false)
            String keyword){
        return trace("searchEmployees",
                "keyword",
                keyword,
                directory.employees(keyword));
    }

    @Tool(description = "依公司英文名稱片段查全部符合的客戶代碼、公司與國家；代碼保留原大小寫及空白。")
    public TableResult searchCustomers(
            @ToolParam(description = "公司英文姓名片段, 查全部的話為 null", required = false)
            String keyword){
        return trace("searchCustomers",
                "keyword",
                keyword,
                directory.customers(keyword));

    }

    @Tool(description = """
        依員工編號查全部訂單，依訂單編號遞減；
        適用使用者已提供員工編號；只有姓名時改用 searchOrdersByEmployeeName。
        """)
    public TableResult searchOrdersByEmployees(
            @ToolParam(description = "員工編號", required = false)
            Integer employeeId){
        return trace("searchOrdersByEmployees",
                "employeeId", employeeId,
                orders.searchByEmployee(employeeId));
    }

    @Tool(description = """
        依客戶代碼查全部訂單，依訂單編號遞減；
        適用使用者已提供客戶代碼；只有公司名時改用 searchOrdersByCustomerName
        """)
    public TableResult searchOrdersCustomers(
            @ToolParam(description = "客戶代碼", required = false)
            String customerId){
        return trace("searchOrdersCustomers",
                "customerId", customerId,
                searchOrdersCustomers(customerId));
    }


    @Tool(description = """
        依員工姓名片段一次取得員工及全部訂單。
        查姓名的訂單優先用此工具。
        多人符合時只回傳候選名單，必須請使用者指定，不可自行選擇或繼續查候選訂單。
        """)
    public TableResult searchOrdersByEmployeeName(
            @ToolParam(description = "員工姓名片段", required = false)
            String keyword){
        return trace("searchOrdersByEmployeeName",
                "keyword", keyword,
                searchOrdersByEmployeeName(keyword));
    }

    @Tool(description = """
        依公司名稱片段一次取得客戶及全部訂單。
        查公司名的訂單優先用此工具。
        多家公司符合時只回傳候選名單，必須請使用者指定，不可自行選擇或繼續查候選訂單。
        """)
    public TableResult searchOrdersByCustomerName(
            @ToolParam(description = "公司名稱片段", required = false)
            String keyword){
        return trace("searchOrdersByCustomerName",
                "keyword", keyword,
                searchOrdersByCustomerName(keyword));

    }

    private TableResult trace(String tool,
                              String parameter,
                              Object value,
                              TableResult result) {
        var arguments = new LinkedHashMap<String, Object>();
        arguments.put(parameter, value);
        traces.add(new ToolTrace(tool, arguments, result));
        return result;
    }


}
