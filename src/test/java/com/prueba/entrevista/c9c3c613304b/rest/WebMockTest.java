package com.prueba.entrevista.c9c3c613304b.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;
import com.prueba.entrevista.c9c3c613304b.exceptions.PriceNotFoundException;
import com.prueba.entrevista.c9c3c613304b.services.PriceService;

@WebMvcTest(PriceController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService mockService;

    @Test
    void priceShouldReturnEmptyDatabase() throws Exception {
        when(mockService.findAll()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/api/v1/prices").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void priceShouldReturnArrayOfOne() throws Exception {
        when(mockService.findAll())
                .thenReturn(Collections.singleton(new PriceDTO(1L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35455L, 0, new BigDecimal(35.5), "EUR")));
        this.mockMvc.perform(get("/api/v1/prices").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].start", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$[0].end", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$[0].productID", is(35455)))
                .andExpect(jsonPath("$[0].priority", is(0)))
                .andExpect(jsonPath("$[0].price", is(35.5)))
                .andExpect(jsonPath("$[0].currency", is("EUR")));
    }

    @Test
    void priceShouldReturnNotFound() throws Exception {
        when(mockService.findOne(anyLong())).thenThrow(PriceNotFoundException.class);
        this.mockMvc.perform(get("/api/v1/prices/id/234").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void priceShouldReturnOne() throws Exception {
        when(mockService.findOne(anyLong())).thenReturn(new PriceDTO(1L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35455L, 0, new BigDecimal(35.5), "EUR"));
        this.mockMvc.perform(get("/api/v1/prices/id/234").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.start", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.end", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.productID", is(35455)))
                .andExpect(jsonPath("$.priority", is(0)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is("EUR")));
    }

    @Test
    void priceShouldReturnNotFoundOnUnknownProductID() throws Exception {
        when(mockService.findBy(anyLong(), any(), any(), any()))
                .thenThrow(PriceNotFoundException.class);
        this.mockMvc.perform(get("/api/v1/prices/product-id/234").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void priceShouldReturnOneOnProductID() throws Exception {
        when(mockService.findBy(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class), anyInt()))
                .thenReturn(Collections.singleton(
                        new PriceDTO(1L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35455L, 0, new BigDecimal(35.5), "EUR")));
        this.mockMvc
                .perform(get("/api/v1/prices/product-id/35455")
                        .param("start-date", "2020-06-14T00:00:00")
                        .param("end-date", "2020-12-31T23:59:59")
                        .param("priority", "0")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].start", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$[0].end", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$[0].productID", is(35455)))
                .andExpect(jsonPath("$[0].priority", is(0)))
                .andExpect(jsonPath("$[0].price", is(35.5)))
                .andExpect(jsonPath("$[0].currency", is("EUR")));
    }

    @Test
    void priceShouldSave() throws Exception {
        PriceDTO request = new PriceDTO(1L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35455L, 0, new BigDecimal(35.5), "EUR");
        when(mockService.save(any(PriceDTO.class)))
                .thenReturn(request);
        this.mockMvc
                .perform(post("/api/v1/prices")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.start", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.end", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.productID", is(35455)))
                .andExpect(jsonPath("$.priority", is(0)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is("EUR")));
    }

    @Test
    void priceShouldUpdate() throws Exception {
        PriceDTO request = new PriceDTO(1L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 35455L, 0, new BigDecimal(35.5), "EUR");
        when(mockService.update(anyLong(), any(PriceDTO.class)))
                .thenReturn(request);
        this.mockMvc
                .perform(put("/api/v1/prices/id/234")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.start", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.end", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.productID", is(35455)))
                .andExpect(jsonPath("$.priority", is(0)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is("EUR")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void priceShouldDelete() throws Exception {
        this.mockMvc
                .perform(delete("/api/v1/prices/id/234")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockService, times(1)).deleteOne(anyLong());
    }
}
