package com.giantlink.grh.repositories;

import com.giantlink.grh.entities.ERole;
import com.giantlink.grh.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole roles);
}
