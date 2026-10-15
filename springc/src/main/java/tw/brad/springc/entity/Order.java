package tw.brad.springc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Orders 的唯讀映射；不直接序列化 Entity，查詢結果轉成 DTO。 */
@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @Column(name = "OrderID")
    private Integer orderId;

    @Column(name = "CustomerID")
    private String customerId;

    @Column(name = "EmployeeID")
    private Integer employeeId;

    @Column(name = "OrderDate")
    private LocalDateTime orderDate;

    @Column(name = "RequiredDate")
    private LocalDateTime requiredDate;

    @Column(name = "ShippedDate")
    private LocalDateTime shippedDate;

    @Column(name = "Freight", precision = 10, scale = 4)
    private BigDecimal freight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeID", insertable = false, updatable = false)
    private Employee employee;

}
