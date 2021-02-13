package com.example.helloworld.resources;

import com.example.helloworld.api.Saying;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import javax.ws.rs.core.Response;

/*
* A Dropwizard application can contain many resource classes, each corresponding to its own URI pattern.
* Just add another @Path-annotated resource class and call environment.jersey().register()  with an instance of the
* new class.
* */

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed // this is for gathering metrics at regular intervals
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @POST
    public Response postValueToDB(String input) {
        if (input == null || input.length() == 0)
            return Response.status(Response.Status.NO_CONTENT).build();

        System.out.println("Got the input in the POST method as : " + (input));

        return Response.status(Response.Status.ACCEPTED).build();
    }
}
