package api.online.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final String id = UUID.randomUUID().toString();

    @OneToOne()
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_item_id")
    private final List<OrderItem> orderItems = List.of();

    private double totalPrice;
    private double vat;
    private double finalTotal;
}
