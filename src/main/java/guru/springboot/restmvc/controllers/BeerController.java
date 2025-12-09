package guru.springboot.restmvc.controllers;

import guru.springboot.restmvc.exceptions.NotFoundException;
import guru.springboot.restmvc.model.Beer;
import guru.springboot.restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;
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
    public Beer getBeerById(@PathVariable UUID id) {
        log.debug("Get Beer by id {}", id);
        return beerService.getBeerById(id)
                .orElseThrow(() -> new NotFoundException("Beer with id " + id + " not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer) {
        log.debug("Creating a new beer...");
        Beer savedBeer = beerService.saveBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<>(savedBeer, headers, HttpStatus.CREATED);
    }

    @PutMapping("/update/{beerId}")
    public ResponseEntity<Beer> updateBeerById(
            @PathVariable("beerId") UUID beerId,
            @RequestBody Beer beer) {
        log.debug("Updating beer using PUT with id: " + beerId);

        Optional<Beer> updatedBeer = beerService.updateBeerUsingPutById(beerId, beer);

        if (updatedBeer.isPresent()) {
            return new ResponseEntity<>(updatedBeer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update/{beerId}")
    public ResponseEntity<Beer> updatePatchBeerById(
            @PathVariable("beerId") UUID beerId,
            @RequestBody Beer beer) {
        log.debug("Updating beer using PATCH with id: " + beerId);

        Optional<Beer> updatedBeer = beerService.updateBeerUsingPatchById(beerId, beer);

        if (updatedBeer.isPresent()) {
            return new ResponseEntity<>(updatedBeer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{beerId}")
    public ResponseEntity<? extends Object> deleteBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Deleting beer with id: " + beerId);

        Optional<Beer> deleteBeer = beerService.deleteBeerById(beerId);

        if (deleteBeer.isPresent()) {
            return new ResponseEntity<>(deleteBeer.get(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException() {
//        log.error("NotFoundExceptionHandler instantiated.");
//        return ResponseEntity.notFound().build();
//    }
}
