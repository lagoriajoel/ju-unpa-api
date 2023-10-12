package juUnpa.API;

import juUnpa.API.Entities.fixture;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);


		fixture fixture=new fixture();


	}

}