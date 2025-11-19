package guru.springboot.restmvc.service;

import guru.springboot.restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);

    List<Beer> getAllBeers();
}
