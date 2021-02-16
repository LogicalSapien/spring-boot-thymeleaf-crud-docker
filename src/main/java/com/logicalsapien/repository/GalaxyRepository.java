package com.logicalsapien.repository;

import com.logicalsapien.entity.Galaxy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyRepository extends CrudRepository<Galaxy, Long> {}
