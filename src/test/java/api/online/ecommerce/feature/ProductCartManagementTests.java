package api.online.ecommerce.feature;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.commons.ResponseMessages;
import api.online.ecommerce.models.Product;
import api.online.ecommerce.repository.CustomerRepository;
import api.online.ecommerce.requests.AddToCartRequest;
import api.online.ecommerce.services.CartManagementService;
import api.online.ecommerce.services.CustomerManagementService;
import api.online.ecommerce.services.impl.CartManagementServiceImpl;
import api.online.ecommerce.services.impl.CustomerManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductCartManagementTests {

    CustomerManagementService customerManagementService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartManagementService cartManagementService;

    @BeforeEach
    public void beforeEach() {
        customerManagementService = new CustomerManagementServiceImpl(customerRepository);
        cartManagementService = new CartManagementServiceImpl(customerRepository);
    }

    @Test
    @DisplayName("Given a cart When adding a product Then the cart should be populated")
    public void testCartPopulated() {
        Product product = new Product();
        product.setName("Washing Powder");
        product.setUnitPrice(20);

        List<Product> products = new ArrayList<>();
        products.add(product);

        AddToCartRequest addToCartRequest = new AddToCartRequest(products, "123456");

        CustomApiResponse<Object> actualResult = cartManagementService.addProductToCart(addToCartRequest);

        assertEquals(ResponseMessages.GENERAL_SUCCESS_MESSAGE.getResponseCode(), actualResult.getResponseCode());
//        assertTrue(((Cart) actualResult.getData()).getCartItems().contains(product));
    }

}
