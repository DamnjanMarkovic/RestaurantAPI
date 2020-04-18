package restaurantIOS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
@ComponentScan({"restaurantIOS"})


public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}

