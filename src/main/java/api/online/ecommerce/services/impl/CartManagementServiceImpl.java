package api.online.ecommerce.services.impl;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.commons.ResponseBuilder;
import api.online.ecommerce.commons.ResponseMessages;
import api.online.ecommerce.repository.CustomerRepository;
import api.online.ecommerce.requests.AddToCartRequest;
import api.online.ecommerce.services.CartManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartManagementServiceImpl implements CartManagementService {
    private static final Logger log = LoggerFactory.getLogger(CartManagementServiceImpl.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CartManagementServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomApiResponse<Object> addProductToCart(AddToCartRequest addToCartRequest) {
//        Customer customer = customerCrudService.findById(addToCartRequest.getCustomer());
//        if(addToCartRequest.getCartItems().size() > 50) {
//            log.error("Products list more than 50");
//            return ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SYSTEM_ERROR, null);
//        }
//        cart.addAllItems(addToCartRequest.getCartItems());
//        cart.setCustomer(addToCartRequest.getCustomer());
        return ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SUCCESS_MESSAGE, null);
    }

    @Override
    public CustomApiResponse<Object> getCartForCustomerById(String customerId) {
        return null;
    }
}
