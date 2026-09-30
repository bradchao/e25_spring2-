package tw.brad.spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.brad.spring2.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String> {
    @Query("""
        SELECT c
        FROM Customer c
        WHERE c.customerId = :id
    """)
    Optional<Customer> findByCustID(@Param("id") String id);
    Optional<Customer> findByCustomerId(@Param("id") String id);

    /*
        動詞 + 介係詞(By) + 屬性名稱
        findByCompanyName(String companyName) => Optional<Customer> / Customer
                                                => List<Customer>
        countByBirthday() => long
        deleteByAccount(String account) => void

        And/Or, IsNull / isNotNull
        findByGenderAndAge(gender, age) => List<>
        findByGenderOrAge(gender, age) => List<>

        OrderBy + 屬性 + Asc/Desc
        findByLastNameOrderByFirstNameAscAndTitleDesc
     */
    List<Customer> findByCompanyName(String companyName);

}
