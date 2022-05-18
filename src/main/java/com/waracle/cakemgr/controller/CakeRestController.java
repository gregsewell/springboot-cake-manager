package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.domain.Cake;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


/**
 * A REST controller to allow cakes to be listed, created and updated.
 */
@RestController
public class CakeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeRestController.class);

    /**
     * For now, just read the cakes.json from the resources directory and store it as a list of Cake objects
     */
    @GetMapping(path="/cakes", produces="application/json")
    public List<Cake> getCakes() throws IOException {

        InputStream inputStream = new ClassPathResource("static/cakes.json").getInputStream();
        String cakeStr = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        LOGGER.info("cakeStr = " + cakeStr);

        ObjectMapper jsonMapper = new ObjectMapper();
        return Arrays.asList(jsonMapper.readValue(cakeStr, Cake[].class));
    }
}
