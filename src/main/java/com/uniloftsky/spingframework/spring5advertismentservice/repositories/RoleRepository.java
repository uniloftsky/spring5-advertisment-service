package com.uniloftsky.spingframework.spring5advertismentservice.repositories;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
