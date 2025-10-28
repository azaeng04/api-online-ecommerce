package api.online.ecommerce.feature;

import static io.restassured.RestAssured.*;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.commons.ResponseBuilder;
import api.online.ecommerce.commons.ResponseMessages;
import api.online.ecommerce.requests.LoginRequest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerAuthorizationTests {
    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void beforeEach() {
    }

    @Test
    @DisplayName("Given a valid customer login When accessing adding a product to the cart Then the customer should be able to access adding a product to the cart")
    public void testCustomerHasAccessToAddingProductToCartEndpoint() throws Exception {
        CustomApiResponse<Object> expectedResponse = ResponseBuilder.buildResponse(ResponseMessages.PRODUCT_CREATED_MESSAGE, null);

        var actualResult =
                given()
                    .baseUri("http://localhost")
                    .port(randomPort)
                    .auth().basic("JohnDoe", "password")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/ecommerce/customer/cart/add")
                    .as(CustomApiResponse.class);

        assertNotNull(actualResult.getData());
        assertEquals(expectedResponse.getHttpStatus(), actualResult.getHttpStatus());
        assertEquals(expectedResponse.getMessage(), actualResult.getMessage());
        assertEquals(expectedResponse.getResponseCode(), actualResult.getResponseCode());
        assertEquals(expectedResponse.getData(), actualResult.getData());
    }

    @Test
    @DisplayName("Given a valid customer login When accessing deleting a product from the cart Then the customer should be able to access deleting a product from the cart")
    public void testCustomerHasAccessToDeleteProductFromCartEndpoint() throws Exception {

    }

    @Test
    @DisplayName("Given a valid customer login When accessing viewing cartItems in their cart Then the customer should be able to view the cartItems in their cart")
    public void testCustomerHasAccessToViewingProductsInCartEndpoint() throws Exception {

    }

    @Test
    @DisplayName("Given a valid customer login When accessing viewing cartItems Then the customer should be able to view the cartItems available")
    public void testCustomerHasAccessToViewingProductsEndpoint() throws Exception {

    }

    @Test
    @DisplayName("Given a valid customer login When checking out Then the customer should be able to check out")
    public void testCustomerHasAccessToCheckingOutWhatIsInTheirCartEndpoint() throws Exception {

    }

    @Test
    @DisplayName("Given a valid customer login When accessing adding a product Then the customer should not be able to add a product to the inventory")
    public void testAdminHasAccessToAddingProductEndpoint() throws Exception {

    }

    @Test
    @DisplayName("Given a valid customer When updating a product Then the customer should not be able to update a product in the inventory")
    public void testAdminHasAccessToUpdateProductEndpoint() throws Exception {

    }

    @Test
    @DisplayName("Given a valid customer When deleting a product Then the customer should not be able to delete a product from the inventory")
    public void testAdminHasAccessToDeletingProductEndpoint() throws Exception {

    }
}
