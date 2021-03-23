package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Role;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        Set<Role> roles = new HashSet<>();
        roleRepository.findAll().iterator().forEachRemaining(roles::add);
        return roles;
    }

    @Override
    public Role findById(Long aLong) {
        Optional<Role> roleOptional = roleRepository.findById(aLong);
        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Expected role not found!");
        }
        return roleOptional.get();
    }

    @Override
    public Role save(Role obj) {
        return roleRepository.save(obj);
    }

    @Override
    public void delete(Role obj) {
        roleRepository.delete(obj);
    }
}
