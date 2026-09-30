package tw.brad.spring2.projection;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface OrderDetialProjection {
    BigDecimal getUnitPrice();
    int getQuantity();

    // SpEL
    @Value("#{target.product.productName}")
    String getProductName();

    @Value("#{target.unitPrice * target.quantity}")
    BigDecimal getSubtotal();
}
