package com.uniloftsky.spingframework.spring5advertismentservice.repositories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
