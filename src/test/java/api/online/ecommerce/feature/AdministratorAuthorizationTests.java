package api.online.ecommerce.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdministratorAuthorizationTests {
    @Test
    @DisplayName("Given a valid administrator login When accessing adding a product Then the administrator should be able to add a product to the inventory")
    public void testAdminHasAccessToAddingProductEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator When updating a product Then the administrator should be able to update a product in the inventory")
    public void testAdminHasAccessToUpdateProductEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator When deleting a product Then the administrator should be able to delete a product from the inventory")
    public void testAdminHasAccessToDeletingProductEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator When viewing cartItems Then the administrator should be able to view cartItems in the inventory")
    public void testAdminHasAccessToViewingProductsEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator login When accessing adding a product to the cart Then the administrator should not be able to access adding a product to the cart")
    public void testCustomerHasAccessToAddingProductToCartEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator login When accessing deleting a product from the cart Then the administrator should not be able to access deleting a product from the cart")
    public void testCustomerHasAccessToDeleteProductFromCartEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator login When accessing viewing cartItems in their cart Then the administrator should not be able to view the cartItems in their cart")
    public void testCustomerHasAccessToViewingProductsInCartEndpoint() {

    }

    @Test
    @DisplayName("Given a valid administrator login When checking out Then the administrator should not be able to check out")
    public void testCustomerHasAccessToCheckingOutWhatIsInTheirCartEndpoint() {

    }
}
