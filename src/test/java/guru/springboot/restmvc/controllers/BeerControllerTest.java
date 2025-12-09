package guru.springboot.restmvc.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import guru.springboot.restmvc.model.Beer;
import guru.springboot.restmvc.model.BeerStyle;
import guru.springboot.restmvc.service.BeerService;

@WebMvcTest(BeerController.class)
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

    private static final String API_BEER = "/api/beer/";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<Beer> beerArgumentCaptor;

    // Metodo di utilità
    private Beer beerBuilder() {
        return Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Test Beer")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456789")
                .price(new java.math.BigDecimal("5.50"))
                .quantityOnHand(20)
                .build();
    }

    // Serializzazione
    @Test
    void testCreateNewBeerWithSerialization() throws JsonProcessingException, Exception {
        Beer beer = beerBuilder();

    //        ObjectMapper om = new ObjectMapper();
    //        om.findAndRegisterModules();

        System.out.println(objectMapper.writeValueAsString(beer));
    }

    // Testare gli endpoint CRUD
    @Test
    void getBeerById() throws Exception {
        Beer beer = beerBuilder();

        // Configura il mock per restituire il testBeer indipendentemente dall'id richiesto
        given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.ofNullable(beer));

        // Effettua la richiesta e verifica il response
        mockMvc.perform(get(API_BEER + beer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(beer.getId().toString()))
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
        mockMvc.perform(get(API_BEER + "all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void testCreateNewBeer() throws Exception {
        Beer beer = beerBuilder();

        given(beerService.saveBeer(any(Beer.class))).willReturn(beer);

        mockMvc.perform(post(API_BEER + "create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.beerName").value("Test Beer"));
    }

    @Test
    void testUpdateBeerUsingPut() throws Exception {
        Beer beer = beerBuilder();

        mockMvc.perform(put(API_BEER + "update/" + beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)));

        verify(beerService).updateBeerUsingPutById(any(UUID.class), any(Beer.class));
    }

    @Test
    void testUpdateBeerUsingPatch() throws Exception {
        Beer beer = beerBuilder();

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "Test name");

        given(beerService.updateBeerUsingPatchById(any(UUID.class), any(Beer.class)))
                .willReturn(Optional.of(beer));

        mockMvc.perform(patch(API_BEER + "update/" + beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                        .andExpect(status().isOk());

        verify(beerService).updateBeerUsingPatchById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());
        assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
    }

    @Test
    void testDeleteBeer() throws Exception {
        Beer beer = beerBuilder();
        given(beerService.deleteBeerById(beer.getId())).willReturn(Optional.of(beer));

        mockMvc.perform(delete(API_BEER + "delete/" + beer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).deleteBeerById(uuidArgumentCaptor.capture());
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
}