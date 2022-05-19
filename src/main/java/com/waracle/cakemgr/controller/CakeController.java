package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.data.CakeEntity;
import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.domain.Cake;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * A REST controller to allow cakes to be listed, created and updated.
 */
@RestController
public class CakeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeController.class);

    private final CakeRepository repository;

    private final ModelMapper mapper;

    public CakeController(CakeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Return the list of all cakes stored in the database
     * @return list of cakes
     * @throws IOException if the cakes cannot be listed
     */
    @GetMapping(path="/cakes", produces="application/json")
    public List<Cake> getCakes() throws IOException {
        UUID requestId = UUID.randomUUID();
        LOGGER.info("[{}] Received /cakes request", requestId);
        List<CakeEntity> cakeEntities = repository.listCakes(requestId);

        // Convert the entities to POJOs for presentation
        // In this case it is trivial, but in general some more complex transformation could be needed
        // between service/data and presentation layer
        return mapper.map(cakeEntities, new TypeToken<List<Cake>>() {}.getType());
    }
}
