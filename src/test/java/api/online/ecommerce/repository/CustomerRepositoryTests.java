package api.online.ecommerce.repository;

import api.online.ecommerce.models.Cart;
import api.online.ecommerce.models.Customer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.show-sql=true",
})
@Testcontainers
public class CustomerRepositoryTests {

    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:latest"))
            .withDatabaseName("testdb").withUsername("root").withPassword("root");

    @Autowired
    CustomerRepository customerRepository;

//    @DynamicPropertySource
//    static void overrideProps(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", mysql::getJdbcUrl);
//        registry.add("spring.datasource.username", mysql::getUsername);
//        registry.add("spring.datasource.password", mysql::getPassword);
//        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
//    }

    @BeforeAll
    public static void globalSetup() {
        mysql.start();
    }

    @AfterAll
    public static void globalTeardown() {
        mysql.stop();
    }

    @Test
    @DisplayName("Given a customer When adding a customer Then the customer should be added with a cart")
    public void testAddingACustomer() {
        Customer customer = new Customer();
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setTotalPrice(0);

        customer = cart.getCustomer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAge(25);
        customer.setCart(cart);

        customerRepository.save(customer);
        var actualResult = customerRepository.findById(customer.getId());

        assertTrue(actualResult.isPresent());
        assertEquals(customer, actualResult.get());
        assertNotNull(actualResult.get().getCart());
    }
}
