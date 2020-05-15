package com.savan.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author SAVAN
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:dbconnection.properties" })
public class HibernateConfig {
	
	@Autowired
	private Environment env;
	
	/**
	 * Initialize SessionFactory 
	 * @return LocalSessionFactoryBean
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.savan.model");	//reading entity class
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
	}

	
	/**
	 * Initialize dataSource 
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		
		return dataSource;
	}
	
	/**
	 * Initialize Transaction Manager 
	 * @param sessionFactory
	 * @return HibernateTransactionManager
	 */
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		
		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}

	/**
	 * Initialize hibernate properties 
	 * @return Properties
	 */
	private final Properties hibernateProperties() {
		
		Properties hibernateProperties = new Properties();
		
		hibernateProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl"));
		hibernateProperties.setProperty("hibernate.connection.pool_size", env.getRequiredProperty("hibernate.batch.size"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		
		return hibernateProperties;
	}
}
