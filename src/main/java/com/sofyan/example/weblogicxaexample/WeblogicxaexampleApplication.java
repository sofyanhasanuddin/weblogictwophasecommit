package com.sofyan.example.weblogicxaexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

@EnableTransactionManagement
@SpringBootApplication( exclude={
		DataSourceAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class} )
@Import(value={Ds1Config.class,Ds2Config.class})
public class WeblogicxaexampleApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WeblogicxaexampleApplication.class, args);
	}
}
