package com.gedalias.controllers;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
@Path("/user")
public class UserController {
    private final List<Map<String, Object>> users;

    public UserController() {
        users = new ArrayList<>();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(users).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> user) {
        users.add(user);
        return Response.status(Response.Status.CREATED).build();
    }
}
