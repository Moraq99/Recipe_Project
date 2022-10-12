package com.example.recipe_project.repo;

import com.example.recipe_project.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u WHERE u.username = :name")
    AppUser findAppUserByUsername(@Param("name") String username);
}
