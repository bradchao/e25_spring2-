package tw.brad.spring2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    private Integer employeeId;
    private String lastName, firstName, title;
    //------------------
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;


}
