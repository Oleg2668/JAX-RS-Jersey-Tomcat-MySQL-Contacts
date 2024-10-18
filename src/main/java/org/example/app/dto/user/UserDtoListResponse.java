package org.example.app.dto.user;

import jakarta.ws.rs.core.Response;
import org.example.app.entity.User;

import java.util.Collections;
import java.util.List;

public record UserDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<User> userList) {

    public static final String SUCCESS_MESSAGE = "User list has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "User list has not been found!";

    public static UserDtoListResponse of(boolean isUserListEmpty, List<User> userList) {
        if (isUserListEmpty)
            return new UserDtoListResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    Response.Status.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE, Collections.emptyList());
        else
            return new UserDtoListResponse(
                Response.Status.OK.getStatusCode(),
                Response.Status.OK.getReasonPhrase(),
                true, SUCCESS_MESSAGE, userList);
    }
}
