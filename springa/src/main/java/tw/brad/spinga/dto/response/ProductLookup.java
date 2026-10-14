package tw.brad.spinga.dto.response;

import tw.brad.spinga.model.Product;

import java.util.List;

public record ProductLookup(List<Product> products, int totalMatches, boolean truncated) {
}
