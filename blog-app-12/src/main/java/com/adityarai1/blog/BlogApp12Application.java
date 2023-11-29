package com.adityarai1.blog;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class BlogApp12Application {

	public static void main(String[] args){
		SpringApplication.run(BlogApp12Application.class, args);
		
	}
    @Bean   // now we can use the model to use the another class object
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
} 