package net.mausberg.recruitingtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"net.mausberg.authentication_framework_backend", "net.mausberg.recruitingtests"})
@EntityScan(basePackages = "net.mausberg.recruitingtests.model")
@EnableJpaRepositories(basePackages = "net.mausberg.recruitingtests.repository")
public class RecruitingtestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitingtestsApplication.class, args);
	}

}
