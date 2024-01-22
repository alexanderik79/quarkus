package com.example.web;

import com.example.pojo.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class ExampleResource {

    @GET
    @Path("/employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") Long id) {
//        return User.findById(id);
        User user=User.findById(id);
        return Response.ok(user).build();
    }

    @POST
    @Path("/employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createEmployee(User user) {
        User.persist(user);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/employee/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateEmployee(@PathParam("id") Long id, User updatedUser) {
        User existingUser = User.findById(id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        return Response.ok(existingUser).build();
    }

    @DELETE
    @Path("/employee/{id}")
    @Transactional
    public String deleteEmployee(@PathParam("id") Long id) {
        User existingUser = User.findById(id);
        if (existingUser == null) {
            return "Employee with id: "+ existingUser.getId() +" not found";
        }
        existingUser.delete();
        return "Employee "+ existingUser.getName() +" was deleted";
    }

//    @Transactional
//    @PostConstruct
//    public void init() {
//        var user=new User();
//        user.setId(1);
//        user.setName("2");
//        user.setSurname("3");
//        userRepository.persist(user);
//    }
}
