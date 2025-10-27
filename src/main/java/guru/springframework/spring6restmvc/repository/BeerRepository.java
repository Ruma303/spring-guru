package guru.springframework.spring6restmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.spring6restmvc.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {}
