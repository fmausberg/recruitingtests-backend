package net.mausberg.recruitingtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.mausberg.recruitingtests.model.QuizQuestion;


@SpringBootApplication
@ComponentScan(basePackages = "net.mausberg.authentication_framework_backend")
public class RecruitingtestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitingtestsApplication.class, args);
		QuizQuestion qq = new QuizQuestion();
	}

}
