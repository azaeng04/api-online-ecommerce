package api.online.ecommerce.rest;

import api.online.ecommerce.commons.CustomApiResponse;
import api.online.ecommerce.requests.AddToCartRequest;
import api.online.ecommerce.requests.CreateCustomerRequest;
import api.online.ecommerce.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "customer")
public class CustomerRest {

    private final CustomerManagementService customerManagementService;

    @Autowired
    public CustomerRest(CustomerManagementService customerManagementService) {
        this.customerManagementService = customerManagementService;
    }

    @GetMapping("get")
    public ResponseEntity<CustomApiResponse<?>> getCustomer(@Param("id") String id) {
        return ResponseEntity.ok(this.customerManagementService.getCustomerById(id));
    }
}
