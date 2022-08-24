package com.example.recipe_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RecipeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeProjectApplication.class, args);
		//ApplicationContext context = SpringApplication.run(RecipeProjectApplication.class, args);
		/*for ( String s : context.getBeanDefinitionNames()){
			System.out.println(s);
		}*/
	}

}
