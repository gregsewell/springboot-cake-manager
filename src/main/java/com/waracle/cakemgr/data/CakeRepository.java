package com.waracle.cakemgr.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * CRUD Repository for Cake persistence
 */
@Component
public class CakeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeRepository.class);

    /**
     * List all cakes
     * @return the list of cakes
     * @throws IOException if the cake list cannot be retrieved
     */
    public List<CakeEntity> listCakes(UUID requestId) throws IOException {

        // This temp implementation just loads some default cake data from a resource file
        // TODO Replace with real hsqlDB JPA implementation
        InputStream inputStream = new ClassPathResource("static/cakes.json").getInputStream();
        String cakeStr = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        LOGGER.debug("cakeStr = " + cakeStr);

        ObjectMapper jsonMapper = new ObjectMapper();
        List<CakeEntity> cakes = Arrays.asList(jsonMapper.readValue(cakeStr, CakeEntity[].class));
        LOGGER.info("[{}] Found {} cakes in database", requestId, cakes.size());

        return cakes;
    }
}
