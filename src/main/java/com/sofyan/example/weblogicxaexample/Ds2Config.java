package com.sofyan.example.weblogicxaexample;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories( transactionManagerRef = "transactionManager", 
						entityManagerFactoryRef = "categoryEntityManagerFactory",
						basePackages="com.sofyan.example.weblogicxaexample.ds2")
public class Ds2Config {
	
	@Bean
    public LocalContainerEntityManagerFactoryBean categoryEntityManagerFactory() {
        
		final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJtaDataSource( dataSource2() );
        bean.setPackagesToScan("com.sofyan.example.weblogicxaexample.ds2");
        
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.put("javax.persistence.transactionType", "JTA");
        props.put("hibernate.transaction.jta.platform", "org.hibernate.service.jta.platform.internal.WeblogicJtaPlatform" );

        bean.setJpaProperties(props);
        
        return bean;
    }

    @Bean("dataSource2")
    public DataSource dataSource2() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("oracle-local");
        return dataSource;
    }

}
