package tw.brad.spinga.model;

import java.math.BigDecimal;

public record Product(
        int productId,
        String productName,
        Integer categoryId,
        String categoryName,
        String quantityPerUnit,
        BigDecimal unitPrice,
        Integer unitsInStock,
        boolean discontinued
) {}
