package com.example.recipe_project.repo;

import com.example.recipe_project.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Long> {



}
