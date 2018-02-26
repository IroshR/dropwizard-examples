package com.iroshnk.dropwizardmongo.resources;

import com.iroshnk.dropwizardmongo.api.request.StatusUpdateAPI;
import com.iroshnk.dropwizardmongo.api.response.CreationResponseAPI;
import com.iroshnk.dropwizardmongo.core.user.UserManagement;
import com.iroshnk.dropwizardmongo.core.user.UserSearch;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Created by HP on 9/17/2017.
 */
@Path("/user_service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(
        value = "User services",
        description = "User management service.",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/dropwizard/student_service"
)
public class UserResource {
    UserManagement userManagement = new UserManagement();
    UserSearch userSearch = new UserSearch();

    @Path("/user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Create User",
            notes = "Services to manage users.",
            httpMethod = "POST",
            response = Object.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created."),
            @ApiResponse(code = 304, message = "Failed to create user.")
    })
    public Response create(@ApiParam @Valid String api) throws URISyntaxException {
        CreationResponseAPI response = userManagement.create(api);
        if (response.isSuccess()) {
            return Response.ok(response.getMessage()).build();
        } else {
            return Response.notModified(response.getMessage()).build();
        }
    }

    @Path("/user")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Update user",
            notes = "Services to manage categories.",
            httpMethod = "PUT",
            response = Object.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated."),
            @ApiResponse(code = 304, message = "Failed to update user.")
    })
    public Response update(@ApiParam @Valid String api) throws URISyntaxException {
        CreationResponseAPI response = userManagement.update(api);
        if (response.isSuccess()) {
            return Response.ok(response.getMessage()).build();
        } else {
            return Response.notModified(response.getMessage()).build();
        }
    }

    @Path("/update_status")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Update status",
            notes = "Services to manage users.",
            httpMethod = "PUT",
            response = CreationResponseAPI.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status updated."),
            @ApiResponse(code = 304, message = "Failed to update status.")
    })
    public Response updateStatus(@ApiParam @Valid StatusUpdateAPI api) throws URISyntaxException {
        CreationResponseAPI response = userManagement.updateStatus(api);
        if (response.isSuccess()) {
            return Response.ok(response.getMessage()).build();
        } else {
            return Response.notModified(response.getMessage()).build();
        }
    }

    @Path("/user/{userId}")
    @GET
    @ApiOperation(
            value = "Find User",
            notes = "Services to manage user.",
            httpMethod = "GET",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful."),
            @ApiResponse(code = 204, message = "No data found.")
    })
    public Response find(@ApiParam @PathParam("userId") long id) throws URISyntaxException {
        Object o = userSearch.searchByPK(id);
        if (o == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(o).build();
        }
    }

    @Path("/user/search/{query}")
    @GET
    @ApiOperation(
            value = "Find users",
            notes = "Services to manage users.",
            httpMethod = "GET",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful."),
            @ApiResponse(code = 204, message = "No data found.")
    })
    public Response findByQuery(@ApiParam @PathParam("query") String query,
                                @ApiParam @QueryParam("skip") @DefaultValue("0") int skip,
                                @ApiParam @QueryParam("limit") @DefaultValue("10") short limit) throws URISyntaxException {
        Object o = userSearch.search(query,limit, skip);
        if (o == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(o).build();
        }
    }
}
