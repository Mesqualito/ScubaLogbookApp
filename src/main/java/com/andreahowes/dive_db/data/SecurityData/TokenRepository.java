package com.andreahowes.dive_db.data.SecurityData;

import com.andreahowes.dive_db.logic.SecurityModule.JWT.Token;
import org.springframework.data.repository.CrudRepository;


public interface TokenRepository extends CrudRepository<Token, Integer> {

    boolean existsByValue(String value);

}
