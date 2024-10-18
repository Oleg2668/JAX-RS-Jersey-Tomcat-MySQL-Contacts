package org.example.app.dto.product;

import jakarta.ws.rs.core.Response;

public record ProductDtoDeleteResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "Product with id %s has been deleted successfully.";
    public static final String FAILURE_MESSAGE = "Product with id %s has not been found!";

    public static ProductDtoDeleteResponse of(Long id, boolean isProductFound) {
        if (isProductFound)
            return new ProductDtoDeleteResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id));
        else
            return new ProductDtoDeleteResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id));
    }
}
