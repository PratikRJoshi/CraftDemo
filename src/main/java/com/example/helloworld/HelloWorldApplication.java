package com.example.helloworld;

import com.example.dao.UserDAO;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.YelpResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);

        environment.jersey().register(new HelloWorldResource(configuration.getTemplate(),
                                                             configuration.getDefaultName(),
                                                             dao));
        environment.jersey().register(new YelpResource(dao));
    }

}