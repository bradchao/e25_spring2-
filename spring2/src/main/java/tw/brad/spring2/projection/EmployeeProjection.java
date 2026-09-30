package tw.brad.spring2.projection;

import java.util.List;

/*
    method name => Entity
 */
public interface EmployeeProjection {
    String getLastName();
    String getFirstName();
    String getTitle();
    List<OrderProjection> getOrders();
}
