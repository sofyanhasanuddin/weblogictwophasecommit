package com.sofyan.example.weblogicxaexample;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories( transactionManagerRef = "transactionManager",
						entityManagerFactoryRef = "productEntityManagerFactory",
						basePackages="com.sofyan.example.weblogicxaexample.ds1")
public class Ds1Config {
	
	@Bean
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory() {
        
		final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJtaDataSource( dataSource() );
        bean.setPackagesToScan("com.sofyan.example.weblogicxaexample.ds1");
        
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.put("javax.persistence.transactionType", "JTA");
        props.put("hibernate.transaction.jta.platform", "org.hibernate.service.jta.platform.internal.WeblogicJtaPlatform" );

        bean.setJpaProperties(props);
        
        return bean;
    }

    @Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("mysql-local");
        return dataSource;
    }

}
