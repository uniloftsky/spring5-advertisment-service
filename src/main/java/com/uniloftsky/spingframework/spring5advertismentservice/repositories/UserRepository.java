package com.uniloftsky.spingframework.spring5advertismentservice.repositories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
