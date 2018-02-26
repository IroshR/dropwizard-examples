package com.iroshnk.dropwizarde.resources;

import com.iroshnk.dropwizarde.DropwizardEConfiguration;
import com.iroshnk.dropwizarde.api.request.CreateUserRequest;
import com.iroshnk.dropwizarde.api.request.UpdateUserRequest;
import com.iroshnk.dropwizarde.api.response.CreationResponse;
import com.iroshnk.dropwizarde.api.response.UpdateResponse;
import com.iroshnk.dropwizarde.core.user.UserManagement;
import com.iroshnk.dropwizarde.util.Status;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Created by HP on 8/11/2017.
 */
@Path("/user_service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(
        value = "User management services",
        description = "User management service.",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/dropwizard/user_service"
)
public class UserResource {
    UserManagement userManagement;

    public UserResource() {
        userManagement = new UserManagement();
    }

    @Path("/user")
    @POST
    @ApiOperation(
            value = "Create user.",
            notes = "User management services.",
            httpMethod = "POST",
            response = CreationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful."),
            @ApiResponse(code = 304, message = "Error occurred.")
    })
    public Response create(@ApiParam @Valid CreateUserRequest api) throws URISyntaxException {
        CreationResponse response = userManagement.create(api);
        if (response.status == Status.RESPONSE_STATUS_SUCCESS) {
            return Response.ok(response).build();
        } else {
            return Response.notModified(response.message).build();
        }
    }

    @Path("/user")
    @PUT
    @ApiOperation(
            value = "Update user.",
            notes = "User management services.",
            httpMethod = "PUT",
            response = UpdateResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful."),
            @ApiResponse(code = 304, message = "Error occurred.")
    })
    public Response create(@ApiParam @Valid UpdateUserRequest api) throws URISyntaxException {
        UpdateResponse response = userManagement.update(api);
        if (response.status == Status.RESPONSE_STATUS_SUCCESS) {
            return Response.ok(response).build();
        } else {
            return Response.notModified(response.message).build();
        }
    }
}
