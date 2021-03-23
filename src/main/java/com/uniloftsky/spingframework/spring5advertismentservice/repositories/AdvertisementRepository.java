package com.uniloftsky.spingframework.spring5advertismentservice.repositories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
}
