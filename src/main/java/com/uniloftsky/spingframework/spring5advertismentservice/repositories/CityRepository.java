package com.uniloftsky.spingframework.spring5advertismentservice.repositories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
