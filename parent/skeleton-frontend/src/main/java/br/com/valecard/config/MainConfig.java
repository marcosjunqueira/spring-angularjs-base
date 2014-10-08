/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.config;

import br.com.valecard.context.event.ChoiceTaskExecutorApplicationEventMulticaster;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.valecard.repositories")
@EnableTransactionManagement
@ComponentScan(basePackages = {"br.com.valecard.repositories", "br.com.valecard.repositories.events", "br.com.valecard.controllers", "br.com.valecard.services", "br.com.valecard.listeners", "br.com.valecard.services.security.impl"}, excludeFilters = {
    @ComponentScan.Filter(Configuration.class)})
@PropertySource("classpath:application.properties")
@Import(RepositoryRestMvcConfiguration.class)
public class MainConfig extends RepositoryRestMvcConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        //Abaixo está configurado para obter propriedades do sistema que podem 
        //ser passadas via opção -Dprop=value do java
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("database.driverClassName"));
        String url = environment.getProperty("MYSQL_URL_AGENDOO");
        if (url.startsWith("mysql://")) {//ajuste para cloudbees funcionar
            url = "jdbc:" + url;
        }
        dataSource.setUrl(url);
        dataSource.setUsername(environment.getProperty("MYSQL_USERNAME_AGENDOO"));
        dataSource.setPassword(environment.getProperty("MYSQL_PASSWORD_AGENDOO"));
        LOG.info("Inicializando datasource: " + dataSource.getUrl());
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.com.valecard.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));

        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(environment.getProperty("mail.port", Integer.class));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));

        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        //Implementação própria para execução de evento de acordo com o tipo, 
        //se for um evento de repositório executa sincronamente 
        //se for outro tipo de evento executa assincronamente
        final ChoiceTaskExecutorApplicationEventMulticaster multicaster = new ChoiceTaskExecutorApplicationEventMulticaster();
        multicaster.setTaskExecutor(taskExecutor());
        return multicaster;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        //configuração para exposição dos repositorios como api json+halC
        try {
            config.setBaseUri(new URI("/data"));
        } catch (URISyntaxException ex) {
            LOG.log(Level.WARNING, "Não foi possível configurar a URI da API JSON.", ex);
        }
    }

    private static final Logger LOG = Logger.getLogger(MainConfig.class.getName());

}
