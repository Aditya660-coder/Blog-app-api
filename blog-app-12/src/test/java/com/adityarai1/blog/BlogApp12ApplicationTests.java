package com.adityarai1.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adityarai1.blog.repositories.UserRepo;

@SpringBootTest
class BlogApp12ApplicationTests {

	@Autowired
	private UserRepo userRepo;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest()
	{
		String ClassName = this.userRepo.getClass().getName();
		System.out.println(ClassName);
	}

}
