package org.example.app.dto.product;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Product;

public record ProductDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product has been created successfully.";
    public static final String FAILURE_MESSAGE = "Product has not been created!";

    public static ProductDtoCreateResponse of(boolean isProductCreated, Product product) {
        if (isProductCreated)
            return new ProductDtoCreateResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, product);
        else
            return new ProductDtoCreateResponse(
                    Response.Status.NO_CONTENT.getStatusCode(),
                    Response.Status.NO_CONTENT.getReasonPhrase(),
                    false, FAILURE_MESSAGE, null);
    }
}
