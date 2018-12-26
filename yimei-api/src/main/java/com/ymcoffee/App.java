package com.ymcoffee;

import com.ymcoffee.dao.hibernate.base.ExtJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.ymcoffee"})
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = ExtJpaRepositoryFactoryBean.class)
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}