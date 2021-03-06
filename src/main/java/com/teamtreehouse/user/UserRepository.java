package com.teamtreehouse.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
