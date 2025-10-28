package api.online.ecommerce.rest;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.commons.ResponseBuilder;
import api.online.ecommerce.commons.ResponseMessages;
import api.online.ecommerce.requests.AddToCartRequest;
import api.online.ecommerce.services.CartManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("cart")
public class CartRest {

    public final CartManagementService cartManagementService;

    @Autowired
    public CartRest(CartManagementService cartManagementService) {
        this.cartManagementService = cartManagementService;
    }

    @PostMapping("add")
    public ResponseEntity<CustomApiResponse<?>> addProductToCart(@RequestBody AddToCartRequest addToCartRequest) {
        if(addToCartRequest.getProducts().size() > 50) {
            return ResponseEntity.ok(ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SYSTEM_ERROR, null));
        }
        return ResponseEntity.ok(ResponseBuilder.buildResponse(ResponseMessages.GENERAL_SUCCESS_MESSAGE, null));
    }
}
