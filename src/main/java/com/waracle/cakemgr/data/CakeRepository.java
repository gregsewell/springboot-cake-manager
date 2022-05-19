package com.waracle.cakemgr.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD Repository for Cake persistence
 */
@Repository
public interface CakeRepository extends CrudRepository<CakeEntity, Long> {

}
