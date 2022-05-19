package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.data.CakeEntity;
import com.waracle.cakemgr.data.CakeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers=CakeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CakeControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CakeRepository repository;

    @Test
    public void testGetCakes_shouldSucceed() throws Exception {

        CakeEntity cake1 = new CakeEntity(1L, "Chocolate", "Chocolate", "chocolate.jpg");
        CakeEntity cake2 = new CakeEntity(2L, "Lemon", "Lemon", "lemon.jpg");
        CakeEntity cake3 = new CakeEntity(3L, "Coffee", "Coffee", "coffee.jpg");

        Mockito.when(repository.listCakes(any())).thenReturn(Arrays.asList(cake1, cake2, cake3));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cakes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)));
    }
}
