package guru.springframework.spring6restmvc.bootstrap;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BootstrapData(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Beer beer1 = Beer.builder()
                .beerName("Beer1")
                .upc("0631234200036")
                .price(new BigDecimal("12.95"))
                .quantityOnHand(200)
                .build();

        Beer beer2 = Beer.builder()
                .beerName("Beer2")
                .upc("0631234300019")
                .price(new BigDecimal("10.99"))
                .quantityOnHand(150)
                .build();

        Beer beer3 = Beer.builder()
                .beerName("Beer3")
                .upc("0631234300026")
                .price(new BigDecimal("8.75"))
                .quantityOnHand(100)
                .build();

        // Save all created beers
        beerRepository.saveAll(List.of(beer1, beer2, beer3));
        System.out.println("Bootstrap data loaded: " + beerRepository.count() + " beers.");
    }
}