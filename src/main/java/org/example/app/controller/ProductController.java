package org.example.app.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.dto.product.*;
import org.example.app.entity.Product;
import org.example.app.service.product.ProductService;

import java.util.Collections;
import java.util.List;

// Вхідна точка (REST-контроллер).
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductService service;

    // Створення нового запису
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final ProductDtoRequest request) {
        Product product = service.create(request);
        if (product != null)
            return Response.ok()
                    .entity(ProductDtoCreateResponse.of(true,
                            product))
                    .build();
        else
            return Response.ok()
                    .entity(ProductDtoCreateResponse.of(false,
                            null))
                    .build();
    }

    // Отримання всіх записів
    @GET
    public Response getAll() {
        List<Product> list = service.getAll();
        if (list.isEmpty())
            return Response.ok()
                    .entity(ProductDtoListResponse.of(true,
                            Collections.emptyList()))
                    .build();
        else
            return Response.ok()
                    .entity(ProductDtoListResponse.of(false,
                            list))
                    .build();
    }


    // ---- Path Params ----------------------

    // Отримання запису за id
    @GET
    @Path("{id: [1-9][0-9]*}")
    public Response getById(@PathParam("id") final Long id) {
        Product product = service.getById(id);
        if (product != null)
            return Response.ok()
                    .entity(ProductDtoByIdResponse.of(id, true,
                            product))
                    .build();
        else
            return Response.ok()
                    .entity(ProductDtoByIdResponse.of(id, false,
                            null))
                    .build();
    }

    // Оновлення запису за id
    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") final Long id, final ProductDtoRequest request) {
        Product productToUpdate = service.getById(id);
        if (productToUpdate != null) {
            Product productUpdated = service.update(id, request);
            return Response.ok()
                    .entity(ProductDtoUpdateResponse.of(id, true,
                            productUpdated))
                    .build();
        } else {
            return Response.ok()
                    .entity(ProductDtoUpdateResponse.of(id, false,
                            null))
                    .build();
        }
    }

    // Видалення запису за id
    @DELETE
    @Path("{id: [0-9]+}")
    public Response deleteById(@PathParam("id") final Long id) {
        if (service.deleteById(id))
            return Response.ok()
                    .entity(ProductDtoDeleteResponse.of(id, true))
                    .build();
        else
            return Response.ok()
                    .entity(ProductDtoDeleteResponse.of(id, false))
                    .build();
    }
}