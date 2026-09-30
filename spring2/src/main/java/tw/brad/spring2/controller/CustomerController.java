package tw.brad.spring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring2.entity.Customer;
import tw.brad.spring2.repository.CustomerRepo;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    @Autowired
    private CustomerRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> test1(@PathVariable String id){
        return ResponseEntity.ok(repo.findById(id).orElse(null));
    }



}
