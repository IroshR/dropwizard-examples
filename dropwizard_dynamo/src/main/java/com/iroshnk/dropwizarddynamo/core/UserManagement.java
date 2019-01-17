package com.iroshnk.dropwizarddynamo.core;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.iroshnk.dropwizarddynamo.api.request.CreateUserRequest;
import com.iroshnk.dropwizarddynamo.api.request.UpdateUserRequest;
import com.iroshnk.dropwizarddynamo.api.response.CreationResponse;
import com.iroshnk.dropwizarddynamo.api.response.UpdateResponse;
import com.iroshnk.dropwizarddynamo.dao.UserDAO;
import com.iroshnk.dropwizarddynamo.model.User;
import com.iroshnk.dropwizarddynamo.util.ModelToApiConverter;
import com.iroshnk.dropwizarddynamo.util.Status;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManagement {
    static Logger logger = Logger.getLogger(UserManagement.class.getName());
    private final UserDAO userDao;

    public UserManagement(AmazonDynamoDBClient client) {
        this.userDao = new UserDAO(client);
    }

    public CreationResponse create(CreateUserRequest api) {
        CreationResponse response = new CreationResponse();

        User old = userDao.getUserByEmail(api.email);
        if(old != null ){
            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = "User email already exist.";

            return response;
        }


        User user = new User();
        user.setFirstName(api.firstName);
        user.setLastName(api.lastName);
        user.setAddress(api.address);
        user.setDateOfBirth(api.dateOfBirth);
        user.setEmail(api.email);
        user.setMobile(api.mobile);
        user.setStatus(String.valueOf(Status.PENDING));
        user.setCreatedDate(String.valueOf(new Date()));


        if (userDao.addUser(user)) {
            response.status = Status.RESPONSE_STATUS_SUCCESS;

            response.data = ModelToApiConverter.convert(user);
            logger.log(Level.INFO, "User created. Id : " + user.getUserId());
        } else {
            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = "User not created.";
            logger.log(Level.WARNING, "User not created.");
        }

        return response;
    }

    public UpdateResponse update(UpdateUserRequest api) {
        UpdateResponse response = new UpdateResponse();

        User old = userDao.getUserByUserName(api.userId);

        if (old == null) {
            response.message = "invalid user id/user doesn't exist";
            response.status = Status.RESPONSE_STATUS_FAIL;

            return response;
        }
        if (old.getStatus().equals(String.valueOf(Status.DELETED))) {
            logger.log(Level.WARNING, "cannot update deleted user : " + api.userId);
            response.message = "cannot update deleted user ";
            response.status = Status.RESPONSE_STATUS_FAIL;

            return response;
        }

        if (api.firstName != null) old.setFirstName(api.firstName);
        if (api.lastName != null) old.setLastName(api.lastName);
        if (api.address != null) old.setAddress(api.address);
        if (api.dateOfBirth != null) old.setDateOfBirth(api.dateOfBirth);
        if (api.mobile != null) old.setMobile(api.mobile);

        if (userDao.update(old)) {
            response.id = api.userId;
            response.status = Status.RESPONSE_STATUS_SUCCESS;
            logger.log(Level.INFO, "User updated . Id : " + api.userId);
        } else {
            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = "User not updated.";
            logger.log(Level.WARNING, "User not updated. ");
        }

        return response;
    }
}
