package org.example.app.dto.user;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.User;

public record UserDtoByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        User user) {

    public static final String SUCCESS_MESSAGE = "User with id %s has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "User with id %s has not been found!";

    public static UserDtoByIdResponse of(Long id, boolean isUserFound, User user) {
        if (isUserFound)
            return new UserDtoByIdResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), user);
        else
            return new UserDtoByIdResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
