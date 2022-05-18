package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.domain.Cake;
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
public class CakeRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeRESTController.class);

    private final CakeRepository repository;

    public CakeRESTController(CakeRepository repository) {
        this.repository = repository;
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
        return repository.listCakes(requestId);
    }
}
