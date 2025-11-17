package guru.springboot.lombok.controllers;

import guru.springboot.lombok.model.Beer;
import guru.springboot.lombok.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by id {}", id);
        return beerService.getBeerById(id);
    }
}
