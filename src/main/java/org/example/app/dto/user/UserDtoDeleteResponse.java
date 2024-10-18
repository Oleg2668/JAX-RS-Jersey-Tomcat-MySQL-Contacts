package org.example.app.dto.user;

import jakarta.ws.rs.core.Response;

public record UserDtoDeleteResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "User with id %s has been deleted successfully.";
    public static final String FAILURE_MESSAGE = "User with id %s has not been found!";

    public static UserDtoDeleteResponse of(Long id, boolean isUserFound) {
        if (isUserFound)
            return new UserDtoDeleteResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id));
        else
            return new UserDtoDeleteResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id));
    }
}
