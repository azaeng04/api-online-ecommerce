package api.online.ecommerce.repository;

import api.online.ecommerce.models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("Given a product When adding a product Then the product should be added")
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Washing powder");
        product.setUnitPrice(50);

        productRepository.save(product);
        var actualResult = productRepository.findById(product.getId());

        assertTrue(actualResult.isPresent());
        assertEquals(product, actualResult.get());
    }
}
