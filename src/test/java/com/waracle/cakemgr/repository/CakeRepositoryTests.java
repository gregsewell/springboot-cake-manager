package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.data.CakeEntity;
import com.waracle.cakemgr.data.CakeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CakeRepositoryTests {

    @Autowired
    private CakeRepository repository;

    @Test
    public void findAllCakes_shouldSucceed() {
        repository.deleteAll();
        repository.save(new CakeEntity(null, "Chocolate", "Chocolate cake", "chocolate.jpg"));
        repository.save(new CakeEntity(null, "Lemon", "Lemon cake", "lemon.jpg"));
        repository.save(new CakeEntity(null, "Coffee", "Coffee cake", "coffee.jpg"));
        Iterable<CakeEntity> cakes = repository.findAll();
        assertTrue(cakes instanceof List);
        assertEquals(3, ((List<CakeEntity>) cakes).size());
    }

    @Test
    public void findCakeById_shouldSucceed() {
        repository.deleteAll();
        CakeEntity cake = new CakeEntity(null, "Chocolate", "Chocolate cake", "chocolate.jpg");
        repository.save(cake);
        long cakeId = cake.getId();

        Optional<CakeEntity> opt = repository.findById(cakeId);
        assertTrue(opt.isPresent());
        cake = opt.get();
        assertEquals(cakeId, cake.getId());
        assertEquals("Chocolate", cake.getTitle());
        assertEquals("Chocolate cake", cake.getDesc());
        assertEquals("chocolate.jpg", cake.getImage());
    }

    @Test
    public void deleteCake_shouldSucceed() {
        repository.deleteAll();
        CakeEntity cake = new CakeEntity(null, "Chocolate", "Chocolate cake", "chocolate.jpg");
        repository.save(cake);
        long cakeId = cake.getId();

        Optional<CakeEntity> opt = repository.findById(cakeId);
        assertTrue(opt.isPresent());
        cake = opt.get();

        repository.delete(cake);
        opt = repository.findById(cakeId);
        assertTrue(opt.isEmpty());
    }
}
