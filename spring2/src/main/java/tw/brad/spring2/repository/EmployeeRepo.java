package tw.brad.spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.brad.spring2.entity.Employee;
import tw.brad.spring2.projection.EmployeeProjection;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<EmployeeProjection> searchByEmployeeId(Integer employeeId);
}
