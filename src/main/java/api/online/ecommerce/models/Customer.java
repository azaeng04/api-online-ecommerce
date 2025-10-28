package api.online.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final String id = UUID.randomUUID().toString();

    private String firstName;
    private String lastName;
    private int age;

    @OneToOne(orphanRemoval = true)
    private Cart cart;
}
