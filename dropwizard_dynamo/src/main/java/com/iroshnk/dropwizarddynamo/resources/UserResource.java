package com.iroshnk.dropwizarddynamo.resources;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.iroshnk.dropwizarddynamo.api.request.CreateUserRequest;
import com.iroshnk.dropwizarddynamo.api.request.UpdateUserRequest;
import com.iroshnk.dropwizarddynamo.api.response.CreationResponse;
import com.iroshnk.dropwizarddynamo.api.response.UpdateResponse;
import com.iroshnk.dropwizarddynamo.core.UserManagement;
import com.iroshnk.dropwizarddynamo.util.Status;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("user_service")
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

    public UserResource(AmazonDynamoDBClient client) {
        userManagement = new UserManagement(client);
    }

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
