package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.data.CakeEntity;
import com.waracle.cakemgr.data.CakeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers=CakeViewController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CakeViewControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CakeRepository repository;

    @Test
    public void testShowCakes_shouldSucceed() throws Exception {

        CakeEntity cake1 = new CakeEntity(1L, "Chocolate", "Chocolate", "chocolate.jpg");
        CakeEntity cake2 = new CakeEntity(2L, "Lemon", "Lemon", "lemon.jpg");
        CakeEntity cake3 = new CakeEntity(3L, "Coffee", "Coffee", "coffee.jpg");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(cake1, cake2, cake3));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("cakes", hasSize(3)))
                .andExpect(model().attribute("cakes", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("title", is("Chocolate")),
                                hasProperty("image", is("chocolate.jpg"))
                        )
                )))
                .andExpect(model().attribute("cakes", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("title", is("Lemon")),
                                hasProperty("image", is("lemon.jpg"))
                        )
                )))
                .andExpect(model().attribute("cakes", hasItem(
                        allOf(
                                hasProperty("id", is(3L)),
                                hasProperty("title", is("Coffee")),
                                hasProperty("image", is("coffee.jpg"))
                        )
                )));
    }
}
