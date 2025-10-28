package api.online.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final String id = UUID.randomUUID().toString();

    @OneToOne
    private Product product;
    private int quantity;
    private double subTotal;

    public void calculateSubtotal() {
        this.subTotal = this.product.getUnitPrice() * quantity;
    }
}
