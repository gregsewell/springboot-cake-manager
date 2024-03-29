package com.waracle.cakemgr.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Loads initial cake data into CakeRepository.
 * Tries to get the latest Cake data from a specified URL
 * If that fails it loads a copy from resources
 */
@Component
public class CakeDataLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeDataLoader.class);

    private static final String CAKE_DATA_FILE = "repository/cakes.json";

    @Value("${cake.data.url}")
    private String cakeDataURL;

    @Value("${download.cake.data:false}")
    private boolean downloadCakeData;

    private CakeRepository repository;

    public CakeDataLoader(CakeRepository repository) {
        this.repository = repository;
    }

    /**
     * When Spring calls this, the CakeRepository bean will have been instantiated, so we can load the latest initial
     * data from cakeDataURL, or load it from the copy in resources, either if that is configured or there is a problem
     * with the data download.
     */
    @PostConstruct
    public void init() throws IOException {

        String cakeStr;
        RestTemplate restTemplate = new RestTemplate();

        if (downloadCakeData) {
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(cakeDataURL, String.class);
                if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
                    cakeStr = response.getBody();
                    LOGGER.info("Loaded cake data from URL {} ", cakeDataURL);
                }
                else {
                    cakeStr = loadCakeDataFromResourceFile();
                }
            }
            catch (Exception ex) {
                cakeStr = loadCakeDataFromResourceFile();
            }
        }
        else {
            cakeStr = loadCakeDataFromResourceFile();
        }
        LOGGER.info("cake data = " + cakeStr);
        ObjectMapper jsonMapper = new ObjectMapper();
        List<CakeEntity> cakes = Arrays.asList(jsonMapper.readValue(cakeStr, CakeEntity[].class));
        repository.saveAll(cakes);
    }

    private String loadCakeDataFromResourceFile() throws IOException {

        LOGGER.info("Failed to load cake data from {}", cakeDataURL);
        LOGGER.info("Loading initial cake data from resource file {}", CAKE_DATA_FILE);
        InputStream inputStream = new ClassPathResource("repository/cakes.json").getInputStream();
        String cakeStr = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        LOGGER.info("Loaded cake data from resource file {} ", CAKE_DATA_FILE);
        return cakeStr;
    }
}
