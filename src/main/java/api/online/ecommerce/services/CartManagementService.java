package api.online.ecommerce.services;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.requests.AddToCartRequest;

public interface CartManagementService {
    CustomApiResponse<Object> addProductToCart(AddToCartRequest addToCartRequest);

    CustomApiResponse<Object> getCartForCustomerById(String customerId);
}
