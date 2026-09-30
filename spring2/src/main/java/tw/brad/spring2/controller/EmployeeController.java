package tw.brad.spring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring2.entity.Employee;
import tw.brad.spring2.projection.EmployeeProjection;
import tw.brad.spring2.repository.EmployeeRepo;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepo repo;

    @GetMapping("/v1/{id}")
    public ResponseEntity<Employee> test1(@PathVariable Integer id){
        return ResponseEntity.ok( repo.findById(id).orElse(null));
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<EmployeeProjection> test2(@PathVariable Integer id){
        EmployeeProjection ep = repo.searchByEmployeeId(id).orElse(null);
        return ResponseEntity.ok(ep);
    }




}
