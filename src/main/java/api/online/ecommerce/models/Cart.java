package api.online.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final String id = UUID.randomUUID().toString();
    @OneToOne
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "cart_item_id")
    private final List<CartItem> cartItems = List.of();
    private double totalPrice;
}

