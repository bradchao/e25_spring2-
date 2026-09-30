package tw.brad.spring2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    private Integer orderId;
    private Date orderDate;
    //--------
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    @JsonBackReference
    private Employee employee;

}
