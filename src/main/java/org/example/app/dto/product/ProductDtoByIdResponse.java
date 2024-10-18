package org.example.app.dto.product;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Product;

public record ProductDtoByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product with id %s has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Product with id %s has not been found!";

    public static ProductDtoByIdResponse of(Long id, boolean isProductFound, Product product) {
        if (isProductFound)
            return new ProductDtoByIdResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), product);
        else
            return new ProductDtoByIdResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
