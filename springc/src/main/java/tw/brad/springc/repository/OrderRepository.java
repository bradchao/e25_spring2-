package tw.brad.springc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tw.brad.springc.entity.Order;

/** 分開依員工或客戶查訂單；回傳全部符合的訂單，不另外執行 COUNT 查詢。 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("""
            select o
            from Order o
            where o.employeeId = :employeeId
            order by o.orderId desc
            """)
    List<Order> findByEmployee(
            @Param("employeeId") Integer employeeId);

    /** 客戶代碼獨立查詢，不與員工條件混合。 */
    @Query("""
            select o
            from Order o
            where o.customerId = :customerId
            order by o.orderId desc
            """)
    List<Order> findByCustomer(
            @Param("customerId") String customerId);
}
