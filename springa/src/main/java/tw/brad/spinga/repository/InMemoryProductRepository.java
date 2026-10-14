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
        String keyword = filter.keyworod() == null ? "" : filter.keyworod().trim();

        List<Product> matches=  products.stream()
                .filter(product -> product.productName().contains(keyword))
                .filter(product -> filter.categoryId()==null ||
                        filter.categoryId().equals(product.categoryId()))
                .filter(product -> isOverPrice(product, filter))
                .filter(product -> !filter.isStockOnly() ||
                        product.unitsInStock() > 0
                        )
                .toList();

        List<Product> page = matches.stream().limit(filter.limit()).toList();

        return new ProductLookup(page, matches.size(), matches.size() > page.size());
    }
    /*
        特定 Product 是否符合設定的價格上限
        true:  符合條件
        false: 不符合
     */
    private boolean isOverPrice(Product product, ProductFilter filter){
        if (filter.maxPrice() == null) return true;
        if (product.unitPrice() == null) return false;
        int compare = product.unitPrice().compareTo(filter.maxPrice());
        /*
         負數: product unirPrice < filter.maxPrice
         0 : product unirPrice == filter.maxPrice
         正 : product unirPrice > filter.maxPrice
         */
        if (filter.macPriceInclusive()){
            return compare <= 0;
        }else{
            return compare < 0;
        }
    }



    @Override
    public Optional<Product> findById(int productId) {
        return products.stream()
                .filter(product -> product.productId() == productId)
                .findFirst();
    }
}
