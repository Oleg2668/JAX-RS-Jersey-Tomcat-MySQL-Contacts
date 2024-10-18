package org.example.app.dto.user;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.User;

public record UserDtoUpdateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        User user) {

    public static final String SUCCESS_MESSAGE = "User with id %s has been updated successfully.";
    public static final String FAILURE_MESSAGE = "User with id %s has not been found!";

    public static UserDtoUpdateResponse of(Long id, boolean isUserFound, User userUpdated) {
        if (isUserFound)
            return new UserDtoUpdateResponse(
                    Response.Status.OK.getStatusCode(),
                    Response.Status.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), userUpdated);
        else
            return new UserDtoUpdateResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
