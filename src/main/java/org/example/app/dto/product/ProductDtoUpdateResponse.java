package org.example.app.dto.product;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Product;

public record ProductDtoUpdateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product with id %s has been updated successfully.";
    public static final String FAILURE_MESSAGE = "Product with id %s has not been found!";

    public static ProductDtoUpdateResponse of(Long id, boolean isProductFound, Product productUpdated) {
        if (isProductFound)
            return new ProductDtoUpdateResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), productUpdated);
        else
            return new ProductDtoUpdateResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
