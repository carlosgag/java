package com.cardcost.persistence;

import com.cardcost.persistence.data.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserData, String> {

    Optional<UserData> findByUsername(String username);
}
