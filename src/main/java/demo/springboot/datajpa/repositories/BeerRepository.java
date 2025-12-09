package demo.springboot.datajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import demo.springboot.datajpa.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID> {}
