package tw.brad.spinga.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductFilter(
        @Size(max = 100) String keyworod,
        @Min(1) @Max(8) Integer categoryId,
        BigDecimal maxPrice,
        Boolean macPriceInclusive,
        Boolean isStockOnly,    // 是否只查有庫存
        @NotNull @Min(1) @Max(10) Integer limit      // 回傳筆數
) {
}
