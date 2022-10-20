package com.example.recipe_project.service;

import com.example.recipe_project.model.Comment;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.repo.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Optional<Comment> getCommentById (Long id){
        return commentRepo.findById(id);
    }

    public void saveNewComment (Comment comment){
        commentRepo.save(comment);
    }

    public List<Comment> getCommentsByRecipe(Recipe recipe){
        return getSortedCommentList(recipe.getComments());
    }

    public List<Comment> getSortedCommentList(List<Comment> tempList){
        return tempList.stream()
                .sorted(Comparator.comparing(Comment :: getCreateComm)
                        .reversed())
                .collect(Collectors.toList());
    }
}
