package com.nnk.springboot;

import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		/*ApplicationContext context =*/ SpringApplication.run(Application.class, args);
		 //UserService service = context.getBean(UserService.class);
		//service.save(new UserDto(1, "admin","admin123" , "administrator", "ADMIN"));

	}
}
