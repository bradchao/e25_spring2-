package tw.brad.springc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tw.brad.springc.entity.Employee;

/** 固定欄位與參數綁定；回傳全部符合資料，不接受任意 SQL。 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("""
        select e 
        from Employee e 
        where lower(concat(e.firstName, ' ', e.lastName)) like :keyword 
        order by e.employeeId
        """)
    List<Employee> search(@Param("keyword") String keyword);
    /** 只有此查詢載入訂單；LEFT JOIN 保留沒有訂單的主體，distinct 避免重複主體。 */
    @Query("""
        select distinct e
        from Employee e
        left join fetch e.orders
        where lower(concat(e.firstName, ' ', e.lastName)) like :keyword
        order by e.employeeId
        """)
    List<Employee> searchWithOrders(@Param("keyword") String keyword);
}
