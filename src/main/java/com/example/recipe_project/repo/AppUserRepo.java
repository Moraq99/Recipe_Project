package com.example.recipe_project.repo;

import com.example.recipe_project.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {

    AppUser findAppUserByUserName(String userName);
}
