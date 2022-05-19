package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.domain.Cake;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * A Spring wev view controller to allow cakes to be listed and created
 */
@Controller
public class CakeViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeViewController.class);

    private final ModelMapper mapper;

    private final CakeRepository repository;

    public CakeViewController(CakeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Show an HTML page containing all cakes
     * @param model mvc model
     * @return cakes page
     */
    @GetMapping(path={"/", "", "index.html"})
    public String showCakes(Model model) {
        LOGGER.debug("Received GET request for cakes as web page");
        List<Cake> cakes = mapper.map(repository.findAll(), new TypeToken<List<Cake>>() {}.getType());
        model.addAttribute("cakes", cakes);
        return "index";
    }
}
