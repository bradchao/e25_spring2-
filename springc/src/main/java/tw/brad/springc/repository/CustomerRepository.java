package tw.brad.springc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tw.brad.springc.entity.Customer;

/** 固定欄位與參數綁定；回傳全部符合資料，不接受任意 SQL。 */
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // JPQL 的 c 是 Customer 實體別名；select、where 與 order by 必須使用同一別名。
    @Query("""
        select c
        from Customer c 
        where lower(c.companyName) like :keyword  
        order by c.customerId
        """)
    List<Customer> search(@Param("keyword") String keyword);
    /** 只有此查詢載入訂單；LEFT JOIN 保留沒有訂單的主體，distinct 避免重複主體。 */
    @Query("""
        select distinct c
        from Customer c
//        left join fetch c.orders
        where lower(c.companyName) like :keyword
        order by c.customerId
        """)
    List<Customer> searchWithOrders(@Param("keyword") String keyword);
}
