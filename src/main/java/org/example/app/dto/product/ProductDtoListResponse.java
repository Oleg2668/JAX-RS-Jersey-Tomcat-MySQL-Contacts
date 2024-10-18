package org.example.app.dto.product;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.Product;

import java.util.Collections;
import java.util.List;

public record ProductDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<Product> productList) {

    public static final String SUCCESS_MESSAGE = "Product list has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Product list has not been found!";

    public static ProductDtoListResponse of(boolean isProductListEmpty, List<Product> productList) {
        if (isProductListEmpty)
            return new ProductDtoListResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE, Collections.emptyList());
        else
            return new ProductDtoListResponse(
                Response.Status.OK.getStatusCode(),
                Response.Status.OK.getReasonPhrase(),
                true, SUCCESS_MESSAGE, productList);
    }
}
