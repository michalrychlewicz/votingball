package com.votingball.votingball;

import com.votingball.votingball.dao.DummyClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@SpringBootApplication
@EnableAspectJAutoProxy
public class VotingballApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingballApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            DummyClass dummyClass = ctx.getBean("dummyClass",DummyClass.class);
            dummyClass.findAll();
            dummyClass.addDummyData();
        };
    }


}

