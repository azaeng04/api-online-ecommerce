package api.online.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private int age;
    private String createdDate;
    private String modifiedDate;
}
