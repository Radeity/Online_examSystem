package dhu.cst.ExamSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(scanBasePackages = {"dhu.cst"})
@EnableCaching
public class ExamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamSystemApplication.class, args);
	}

}
