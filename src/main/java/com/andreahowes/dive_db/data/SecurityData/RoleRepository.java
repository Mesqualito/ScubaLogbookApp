package com.andreahowes.dive_db.data.SecurityData;

import com.andreahowes.dive_db.logic.SecurityModule.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findOneByDescription(String description);
}
