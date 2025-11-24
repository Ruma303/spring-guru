package guru.springboot.restmvc.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;
import java.util.UUID;

import guru.springboot.restmvc.model.Beer;
import guru.springboot.restmvc.model.BeerStyle;
import guru.springboot.restmvc.service.BeerService;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        // Crea un oggetto Beer di test
        UUID beerId = UUID.randomUUID();
        Beer testBeer = Beer.builder()
                .id(beerId)
                .beerName("Test Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456789")
                .price(new java.math.BigDecimal("5.50"))
                .quantityOnHand(20)
                .build();

        // Configura il mock per restituire il testBeer indipendentemente dall'id richiesto
        given(beerService.getBeerById(any(UUID.class))).willReturn(testBeer);

        // Effettua la richiesta e verifica il response
        mockMvc.perform(get("/api/beer/" + beerId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(beerId.toString()))
                .andExpect(jsonPath("$.beerName").value("Test Beer"))
                .andExpect(jsonPath("$.beerStyle").value("PALE_ALE"))
                .andExpect(jsonPath("$.upc").value("123456789"))
                .andExpect(jsonPath("$.quantityOnHand").value(20));
    }

    @Test
    void testListBeers() throws Exception {
        List<Beer> testBeers = List.of(
                Beer.builder().id(UUID.randomUUID()).beerName("Punk IPA").beerStyle(BeerStyle.IPA).upc("123456").price(new java.math.BigDecimal("5.99")).quantityOnHand(85).build(),
                Beer.builder().id(UUID.randomUUID()).beerName("Guinness Draught").beerStyle(BeerStyle.STOUT).upc("789012").price(new java.math.BigDecimal("6.50")).quantityOnHand(150).build(),
                Beer.builder().id(UUID.randomUUID()).beerName("Weihenstephaner").beerStyle(BeerStyle.WHEAT).upc("345678").price(new java.math.BigDecimal("4.99")).quantityOnHand(200).build()
        );
        given(beerService.getAllBeers()).willReturn(testBeers);

        // Il mapping corretto per la lista è "/api/beer/all"
        mockMvc.perform(get("/api/beer/all")
                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }
}