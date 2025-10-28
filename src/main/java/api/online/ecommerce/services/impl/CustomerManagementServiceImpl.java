package api.online.ecommerce.services.impl;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.commons.ResponseBuilder;
import api.online.ecommerce.commons.ResponseMessages;
import api.online.ecommerce.models.Customer;
import api.online.ecommerce.repository.CustomerRepository;
import api.online.ecommerce.services.CustomerManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManagementServiceImpl implements CustomerManagementService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public CustomApiResponse<Object> getCustomerById(String customerId) {
        if(customerId == null) {
            return ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SYSTEM_ERROR, null);
        }

        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.ifPresent(value -> System.out.println("Customer name: " + value.getFirstName() + " " + value.getLastName()));

        System.out.println("Customer ID: " + customerId);

        return customer
                .<CustomApiResponse<Object>>map(value -> ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SUCCESS_MESSAGE, value))
                .orElseGet(() -> ResponseBuilder.buildResponse(ResponseMessages.CUSTOMER_NOT_FOUND, null));
    }

}
