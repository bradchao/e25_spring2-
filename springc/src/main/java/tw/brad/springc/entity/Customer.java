package tw.brad.springc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/** Customers 的唯讀映射；不直接序列化 Entity，查詢結果轉成 DTO。 */
@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @Column(name = "CustomerID")
    private String customerId;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();


}
