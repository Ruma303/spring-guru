package demo.springboot.datajpa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import demo.springboot.datajpa.model.Beer;
import demo.springboot.datajpa.model.BeerStyle;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    private Beer beerBuilder() {
        return Beer.builder()
                .beerName("Test Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456789")
                .price(new BigDecimal("5.50"))
                .quantityOnHand(20)
                .build();
    }

    @Test
    void saveBeerTest() {
        Beer beer = beerRepository.save(beerBuilder());
        assertThat(beer).isNotNull();
        assertThat(beer.getId()).isNotNull();
    }
}