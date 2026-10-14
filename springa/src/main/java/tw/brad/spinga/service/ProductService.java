package tw.brad.spinga.service;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.brad.spinga.dto.request.ProductFilter;
import tw.brad.spinga.dto.response.ProductLookup;
import tw.brad.spinga.repository.ProductRepository;

/*
 不透過 AI
 */
@Service
public class ProductService {
    private final ProductRepository repository;
    private final Validator validator;

    public ProductService(ProductRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }

    public ProductLookup search(ProductFilter filter){

        return repository.search(filter);
    }

    public ProductLookup findById(Integer productId){
        var products = repository.findById(productId).stream().toList();
        return new ProductLookup(products, products.size(), false);
    }



}
