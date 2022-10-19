package com.example.recipe_project.controller;

import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.model.Comment;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.CommentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create-comment")
    public String saveNewComment (@RequestParam("comment") String comment,
                                  Recipe recipe){
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setRecipe(recipe);
        newComment.setCreateComm(LocalDateTime.now());
        newComment.setAppUser((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        recipe.addAComment(newComment);

        commentService.saveNewComment(newComment);

        return "redirect:/recipe/" + recipe.getId();
    }

}
