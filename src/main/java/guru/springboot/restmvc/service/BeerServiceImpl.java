package guru.springboot.restmvc.service;

import guru.springboot.restmvc.model.Beer;
import guru.springboot.restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer_1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Punk IPA")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("5.99"))
                .quantityOnHand(85)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer_2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Guinness Draught")
                .beerStyle(BeerStyle.STOUT)
                .upc("789012")
                .price(new BigDecimal("6.50"))
                .quantityOnHand(150)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer_3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Weihenstephaner")
                .beerStyle(BeerStyle.WHEAT)
                .upc("345678")
                .price(new BigDecimal("4.99"))
                .quantityOnHand(200)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer_1.getId(), beer_1);
        beerMap.put(beer_2.getId(), beer_2);
        beerMap.put(beer_3.getId(), beer_3);
    }

    @Override
    public List<Beer> getAllBeers() {
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by ID: {}", id.toString());
        return beerMap.get(id);
    }
}
