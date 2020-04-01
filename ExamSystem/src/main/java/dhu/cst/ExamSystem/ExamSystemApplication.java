package dhu.cst.ExamSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"dhu.cst"})
public class ExamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamSystemApplication.class, args);
	}

}
