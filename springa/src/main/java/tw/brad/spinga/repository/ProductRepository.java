package tw.brad.spinga.repository;

import tw.brad.spinga.dto.request.ProductFilter;
import tw.brad.spinga.dto.response.ProductLookup;
import tw.brad.spinga.model.Product;

import java.util.Optional;

public interface ProductRepository {
    ProductLookup search(ProductFilter filter);
    Optional<Product> findById(int productId);
}
