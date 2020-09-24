package com.andreahowes.dive_db.data.SecurityData;

import com.andreahowes.dive_db.logic.SecurityModule.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
