package com.cognizant.authentication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<Authentication,String> {
}
