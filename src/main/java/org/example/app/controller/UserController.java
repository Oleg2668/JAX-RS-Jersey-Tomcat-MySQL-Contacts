package org.example.app.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.dto.user.*;
import org.example.app.entity.User;
import org.example.app.service.user.UserService;

import java.util.Collections;
import java.util.List;

// Вхідна точка (REST-контроллер)
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;

    // Створення нового запису
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(final UserDtoRequest request) {
        User user = userService.create(request);
        if (user != null)
            return Response.ok()
                    .entity(UserDtoCreateResponse.of(true, user))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoCreateResponse.of(false, null))
                    .build();
    }

    // Отримання всіх записів
    @GET
    public Response getAllUsers() {
        List<User> list = userService.getAll();
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }


    // ---- Path Params ----------------------

    // Отримання запису за id
    @GET
    @Path("{id: [1-9][0-9]*}")
    public Response getUserById(@PathParam("id") final Long id) {
        User user = userService.getById(id);
        if (user != null)
            return Response.ok()
                    .entity(UserDtoByIdResponse.of(id, true, user))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoByIdResponse.of(id, false, null))
                    .build();
    }

    // Оновлення запису за id
    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUserById(@PathParam("id") final Long id, final UserDtoRequest request) {
        User userToUpdate = userService.getById(id);
        if (userToUpdate != null) {
            User userUpdated = userService.update(id, request);
            return Response.ok()
                    .entity(UserDtoUpdateResponse.of(id, true, userUpdated))
                    .build();
        } else {
            return Response.ok()
                    .entity(UserDtoUpdateResponse.of(id, false, null))
                    .build();
        }
    }

    // Видалення запису за id
    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteUserById(@PathParam("id") final Long id) {
        if (userService.deleteById(id))
            return Response.ok()
                    .entity(UserDtoDeleteResponse.of(id, true))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoDeleteResponse.of(id, false))
                    .build();
    }


    // ---- Query Params ----------------------

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-firstname?firstName=Tom
        If firstName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-firstname?firstName=Tomas
    */
    @GET
    @Path("/query-by-firstname")
    public Response fetchByFirstName(@QueryParam("firstName") final String firstName) {
        List<User> list = userService.fetchByFirstName(firstName);
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Bright
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Terra
        If lastName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Mars
    */
    @GET
    @Path("/query-by-lastname")
    public Response fetchByLastName(@QueryParam("lastName") final String lastName) {
        List<User> list = userService.fetchByLastName(lastName);
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-order-by?orderBy=firstName
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-order-by?orderBy=lastName
    */
    @GET
    @Path("/query-order-by")
    public Response fetchAllOrderBy(@QueryParam("orderBy") final String orderBy) {
        List<User> list = userService.fetchAllOrderBy(orderBy);
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }


    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname-order-by?lastName=Bright&orderBy=firstName
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname-order-by?lastName=Bright&orderBy=email
        If lastName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname-order-by?lastName=Mars&orderBy=firstName
    */
    @GET
    @Path("/query-by-lastname-order-by")
    public Response fetchByLastNameOrderBy(
            @QueryParam("lastName") final String lastName,
            @QueryParam("orderBy") final String orderBy
    ) {
        List<User> list =
                userService.fetchByLastNameOrderBy(lastName, orderBy);
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-between-ids?from=3&to=6
        If ids do not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-between-ids?from=9&to=12
    */
    @GET
    @Path("/query-between-ids")
    public Response fetchBetweenIds(
            @QueryParam("from") final int from,
            @QueryParam("to") final int to
    ) {
        List<User> list = userService.fetchBetweenIds(from, to);
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-lastname-in?lastName1=Terra&lastName2=Bright
        If lastName1 does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-lastname-in?lastName1=Mars&lastName2=Bright
        If lastName2 does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-lastname-in?lastName1=Terra&lastName2=Forest
        If lastName1 and lastName2 do not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-lastname-in?lastName1=Mars&lastName2=Forest
    */
    @GET
    @Path("/query-lastname-in")
    public Response fetchLastNameIn(
            @QueryParam("lastName1") final String lastName1,
            @QueryParam("lastName2") final String lastName2
    ) {
        List<User> list =
                userService.fetchLastNameIn(lastName1, lastName2);
        if (list.isEmpty())
            return Response.ok()
                    .entity(UserDtoListResponse.of(true, Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(UserDtoListResponse.of(false, list))
                    .build();
    }
}