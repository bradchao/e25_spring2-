package tw.brad.spring9.dto.response;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tw.brad.spring9.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public record ProductCriteria(
        @Size(max = 100) String keyword,
        Category category,
        @DecimalMin("0") BigDecimal maxPrice,
        Boolean maxPriceInclusive,
        @Pattern(regexp = "[A-Z]{3}") String currency,
        List<String> memo
) {}
