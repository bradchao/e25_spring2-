package tw.brad.spinga.repository;

import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.json.JsonMapper;
import tw.brad.spinga.dto.request.ProductFilter;
import tw.brad.spinga.dto.response.ProductLookup;
import tw.brad.spinga.model.Product;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository{
    private final List<Product> products;

    public InMemoryProductRepository() throws IOException {
        try (var input = new ClassPathResource("data/products.json").getInputStream()){
            Product[] products = JsonMapper.builder().build().readValue(input, Product[].class);
            this.products = List.copyOf(Arrays.asList(products));
        }
    }


    @Override
    public ProductLookup search(ProductFilter filter) {
        return null;
    }

    @Override
    public Optional<Product> findById(int productId) {
        return Optional.empty();
    }
}
