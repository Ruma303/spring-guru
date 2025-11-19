package guru.springboot.restmvc.service;

import guru.springboot.restmvc.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
    List<Beer> getAllBeers();

    Beer saveBeer(Beer beer);

    Optional<Beer> updateBeerUsingPutById(UUID beerId, Beer beer);
    Optional<Beer> updateBeerUsingPatchById(UUID beerId, Beer beer);

    Optional<Beer> deleteBeerById(UUID beerId);
}
