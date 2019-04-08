package com.voroshen.mapspotinfohunterapi.repository;

import com.voroshen.mapspotinfohunterapi.model.Role;
import com.voroshen.mapspotinfohunterapi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleName);
}