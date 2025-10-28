package api.online.ecommerce.feature;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.commons.ResponseBuilder;
import api.online.ecommerce.commons.ResponseMessages;
import api.online.ecommerce.models.Customer;
import api.online.ecommerce.repository.CustomerRepository;
import api.online.ecommerce.services.CustomerManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerManagementTests {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerManagementService customerManagementService;

    @Test
    @DisplayName("Given a customer ID When searching for the customer Then the customer should be found")
    public void testCustomerIsFound() {
        Customer customer = new Customer();
        customerRepository.save(customer);

        CustomApiResponse<Object> expectedResult = ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SUCCESS_MESSAGE, customer);

        var actualResult = customerManagementService.getCustomerById(customer.getId());

        assertEquals(expectedResult.getResponseCode(), actualResult.getResponseCode());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
        assertEquals(expectedResult.getHttpStatus(), actualResult.getHttpStatus());
        assertEquals(expectedResult.getData(), actualResult.getData());
    }

    @Test
    @DisplayName("Given a customer ID that does not exist When searching for the customer Then the customer should not be found")
    public void testCustomerNotFound() {
        CustomApiResponse<Object> expectedResult = ResponseBuilder.buildResponse(ResponseMessages.CUSTOMER_NOT_FOUND, null);

        var actualResult = customerManagementService.getCustomerById("NOT_FOUND");

        assertEquals(expectedResult.getResponseCode(), actualResult.getResponseCode());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
        assertEquals(expectedResult.getHttpStatus(), actualResult.getHttpStatus());
        assertEquals(expectedResult.getData(), actualResult.getData());
    }
}
