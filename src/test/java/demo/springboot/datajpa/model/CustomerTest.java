package demo.springboot.datajpa.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import demo.springboot.datajpa.repositories.CustomerRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerTest {

    @Autowired
    CustomerRepository customerRepository;

    private Customer customerBuilder() {
        return Customer.builder()
                .name("John")
                .build();
    }

    @Test
    void saveCustomerTest() {
        Customer customer = customerRepository.save(customerBuilder());
        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNotNull();
    }
}