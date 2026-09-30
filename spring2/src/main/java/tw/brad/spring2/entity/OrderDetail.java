package tw.brad.spring2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailPK.class)
@Data
public class OrderDetail {
    @Id
    @Column(name = "OrderID")
    private int orderId;

    @Id
    @Column(name = "ProductID")
    private int productId;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;

    @Column(name = "Quantity")
    private int quantity;
    //--------------------------
    @ManyToOne
    @JoinColumn(name = "OrderID")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;
}
