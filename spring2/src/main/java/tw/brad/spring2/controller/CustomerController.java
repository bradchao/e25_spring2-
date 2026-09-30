package tw.brad.spring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring2.entity.Customer;
import tw.brad.spring2.repository.CustomerRepo;

import java.util.Optional;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    @Autowired
    private CustomerRepo repo;

    @GetMapping("/v1/{id}")
    public ResponseEntity<Customer> test1(@PathVariable String id){
        return ResponseEntity.ok(repo.findById(id).orElse(null));
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<Customer> test2(@PathVariable String id){
        return ResponseEntity.ok(repo.findByCustomerId(id).orElse(null));
    }

    @GetMapping("/v3/{id}")
    public ResponseEntity<Customer> test3(@PathVariable String id){
        return ResponseEntity.ok(repo.findByCustID(id).orElse(null));
    }

    @GetMapping("/v4/{id}")
    public ResponseEntity<Customer> test4(@PathVariable String id){
        Optional<Customer> opt = repo.findById(id);
        if (opt.isPresent()){
            Customer customer = opt.get();
        }
        //repo.findByCustID(id).orElse(new Customer());
        //repo.findByCustID(id).orElseGet(Customer::new);

        return ResponseEntity.ok(
                repo.findByCustomerId(id).orElseThrow(()-> new IllegalArgumentException("ID ERROR")));
    }


}
