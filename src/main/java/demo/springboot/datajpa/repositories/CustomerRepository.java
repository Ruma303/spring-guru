package demo.springboot.datajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import demo.springboot.datajpa.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {}
