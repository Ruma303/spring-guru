package guru.springboot.restmvc.controllers;

import guru.springboot.restmvc.model.Beer;
import guru.springboot.restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/all")
    public List<Beer> getAllBeers() {
        log.debug("Getting all beers");
        return beerService.getAllBeers();
    }

    @GetMapping(value = "/{id}")
    // @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable UUID id) {
        log.debug("Get Beer by id {} 123", id);
        return beerService.getBeerById(id);
    }

    @PostMapping("/create")
    //@RequestMapping(value = "", method = RequestMethod.POST)
    public Beer createBeer(@RequestBody Beer beer) {
        log.debug("Creating a new beer...");
        Beer savedBeer = beerService.saveBeer(beer);
        return savedBeer;
    }

}
