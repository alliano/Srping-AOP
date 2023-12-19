package sprig.aop.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // mengkatifkan fitur AOP AspectJ
@SpringBootApplication
public class AopApplication {
	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}
}
