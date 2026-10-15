package tw.brad.springc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/** Employees 的唯讀映射；不直接序列化 Entity，查詢結果轉成 DTO。 */
@Entity
@Table(name = "Employees")
@Data
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    private Integer employeeId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Title")
    private String title;

    @Column(name = "Country")
    private String country;

    @Column(name = "ReportsTo")
    private Integer reportsTo;

    // ReportsTo 是員工自我關聯；不輸出生日、電話、地址或薪資。
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportsTo", insertable = false, updatable = false)
    private Employee manager;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Employee> subordinates = new ArrayList<>();
    //------------------------------------------

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();


}
