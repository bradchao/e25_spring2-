package tw.brad.spinga.tool;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kotlin.collections.ArrayDeque;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import tw.brad.spinga.dto.request.ProductFilter;
import tw.brad.spinga.dto.response.ProductLookup;
import tw.brad.spinga.dto.response.ToolTrace;
import tw.brad.spinga.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductTool {

    private final ProductService service;
    private final List<ToolTrace> traces = new ArrayList<>();


    public ProductTool(ProductService service){this.service = service;}

    @Tool(description = """
            查詢商品. 名稱是英文字串, 依照編號排序.
            類別: 1飲料 2調味料 3甜點 4乳製品 5穀類 6肉類 7農產品 8海鮮
            """)
    public ProductLookup searchProduct(
            @ToolParam(description = "英文名稱片段, 沒有的話就是 null", required = false)
            String keyword,

            @ToolParam(description = "類別 ID 1~8, 沒有的話就是 null", required = false)
            Integer categoryId,

            @ToolParam(description = "非負數的上限值, 有值得話, macPriceInclusive必須為 true, 沒有的話, 則為 null")
            BigDecimal maxPrice,

            @ToolParam(description = """
                與 maxPrice 一起看: 低於/小於為 false; 不超過/以下為 true.
                例如: 低於 20 為 maxPrice=20, macPriceInclusive=false.
                有價格上限時, 不可為 null;
                只有梅價格上限的時候, 才可以 null
                """, required = false)
            Boolean macPriceInclusive,

            // 否只查有庫存
            @ToolParam(description = "指查有庫存的為 true, 未指定為 null", required = false)
            Boolean isStockOnly,    // 是

            @ToolParam(description = "回傳筆數 1~10, 未指定為 5")
            Integer limit
    ){
        ProductFilter filter =
                new ProductFilter(keyword,categoryId, maxPrice, macPriceInclusive, isStockOnly, limit);

        ProductLookup result = service.search(filter);
        traces.add(new ToolTrace("searchProduct", filter, result));

        return result;
    }

    @Tool(description = "依照商品編號查詢出商品的價格,庫存等等; 找不到為 null")
    public ProductLookup getProductById(
            @ToolParam(description = "商品編號, 必須大於 0") Integer productId
    ){
        ProductLookup result = service.findById(productId);
        traces.add(new ToolTrace("getProductId", Map.of("productId", productId), result));
        return result;
    }

    public List<ToolTrace> getTracesList(){
        return List.copyOf(traces);
    }



}
