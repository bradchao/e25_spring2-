package tw.brad.spring2.projection;

import java.util.Date;
import java.util.List;

public interface OrderProjection {
    Integer getOrderId();
    Date getOrderDate();
    List<OrderDetialProjection> getOrderDetails();
}
