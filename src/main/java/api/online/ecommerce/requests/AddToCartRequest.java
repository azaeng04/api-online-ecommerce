package api.online.ecommerce.requests;

import api.online.ecommerce.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddToCartRequest {
    private List<Product> products;
    private String customerId;
}
