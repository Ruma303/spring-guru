package guru.springboot.restmvc.service;

import guru.springboot.restmvc.model.Beer;
import guru.springboot.restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Beer saveBeer(Beer beer) {
        UUID beerId = beer.getId() != null ? beer.getId() : UUID.randomUUID();

        Beer newBeer = Beer.builder()
                .id(beerId)
                .version(beer.getVersion())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdDate(beer.getCreatedDate())
                .updateDate(beer.getUpdateDate())
                .build();

        beerMap.put(newBeer.getId(), newBeer);
        log.debug("New beer created");

        return newBeer;
    }

    @Override
    public Optional<Beer> updateBeerUsingPutById(UUID beerId, Beer beer) {
        // Controllo se la birra esiste
        Beer existing = beerMap.get(beerId);
        if (existing == null) {
            log.debug("Beer with id {} not found, cannot update", beerId);
            return Optional.empty();
        }

        Beer updatedBeer = Beer.builder()
                .id(beerId) // Mantiene l'id originale!
                .version(beer.getVersion() != null ? beer.getVersion() : existing.getVersion())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdDate(existing.getCreatedDate())
                .updateDate(java.time.LocalDateTime.now())
                .build();

        beerMap.put(beerId, updatedBeer);
        log.debug("Beer with id {} updated", beerId);

        return Optional.of(updatedBeer);
    }

    @Override
    public Optional<Beer> updateBeerUsingPatchById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);
        if (existing == null) {
            log.debug("Beer with id {} not found, cannot update", beerId);
            return Optional.empty();
        }

        if (StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }
        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }
        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }
        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }
        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }

        // Best practice: aggiornare sempre la data di update
        existing.setUpdateDate(LocalDateTime.now());

        beerMap.put(beerId, existing);
        log.debug("Beer with id {} updated", beerId);

        return Optional.of(existing);
    }

    @Override
    public Optional<Beer> deleteBeerById(UUID beerId) {
        Beer existing = beerMap.get(beerId);
        if (existing == null) {
            log.debug("Beer with id {} not found, cannot delete", beerId);
            return Optional.empty();
        }

        Beer deletedBeer = beerMap.remove(beerId);
        log.debug("Beer with id {} deleted", beerId);

        return Optional.of(deletedBeer);
    }
}
