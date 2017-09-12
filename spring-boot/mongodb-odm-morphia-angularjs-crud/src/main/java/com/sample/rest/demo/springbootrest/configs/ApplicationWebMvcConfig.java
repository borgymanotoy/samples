package com.sample.rest.demo.springbootrest.configs;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class ApplicationWebMvcConfig extends WebMvcConfigurerAdapter {

    // Inject an instance of Spring-Boot MongoProperties
    @Autowired
    private MongoProperties mongoProperties;

    private Morphia morphia() {
        final Morphia morphia = new Morphia();
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("com.sample.rest.demo.springbootrest");
        new ValidationExtension(morphia);

        return morphia;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Bean
    public Datastore datastore(MongoClient mongoClient) {
        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
        datastore.ensureIndexes();

        return datastore;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }
}
