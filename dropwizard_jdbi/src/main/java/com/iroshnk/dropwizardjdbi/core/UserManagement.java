package com.iroshnk.dropwizardjdbi.core;

import com.iroshnk.dropwizardjdbi.api.request.CreateUserRequest;
import com.iroshnk.dropwizardjdbi.api.request.UpdateUserRequest;
import com.iroshnk.dropwizardjdbi.api.response.CreationResponse;
import com.iroshnk.dropwizardjdbi.api.response.UpdateResponse;
import com.iroshnk.dropwizardjdbi.dao.UserDao;
import com.iroshnk.dropwizardjdbi.model.User;
import com.iroshnk.dropwizardjdbi.util.ModelToApiConverter;
import com.iroshnk.dropwizardjdbi.util.Status;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.Transaction;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by HP on 8/11/2017.
 */
public class UserManagement {
    static Logger logger = Logger.getLogger(UserManagement.class.getName());
    private final UserDao userDao;

    public UserManagement(DBI dbi) {
        this.userDao = dbi.onDemand(UserDao.class);
    }

    @Transaction
    public CreationResponse create(CreateUserRequest api){
        CreationResponse response = new CreationResponse();

        User user = new User();
        user.setFirstName(api.firstName);
        user.setLastName(api.lastName);
        user.setAddress(api.address);
        user.setDateOfBirth(api.dateOfBirth);
        user.setEmail(api.email);
        user.setMobile(api.mobile);
        user.setStatus(Status.PENDING);
        user.setCreatedDate(new Date());


        try {
            int userId = userDao.createUser(user.getAddress(),user.getFirstName(),user.getStatus(),user.getCreatedDate(),
                    user.getLastName(),user.getMobile(),user.getEmail(), user.getDateOfBirth());

            response.status = Status.RESPONSE_STATUS_SUCCESS;
            response.id = userId;
            user.setUserId(userId);
            response.data = ModelToApiConverter.convert(user);
            logger.log(Level.INFO, "User created. Id : " + user.getUserId());
        } catch (Exception e) {;
            e.printStackTrace();

            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = e.getMessage();
            logger.log(Level.WARNING, "User not created. error : " + e.getMessage());
        }

        return response;
    }

    @Transaction
    public UpdateResponse update(UpdateUserRequest api){
        UpdateResponse response = new UpdateResponse();

        User old = userDao.getUserDetails(api.userId);

        if(old == null){
            response.message = "invalid user id/user doesn't exist";
            response.status = Status.RESPONSE_STATUS_FAIL;

            return response;
        }
        if (old.getStatus() == Status.DELETED) {
            logger.log(Level.WARNING, "cannot update deleted user : " + api.userId);
            response.message = "cannot update deleted user ";
            response.status = Status.RESPONSE_STATUS_FAIL;

            return response;
        }

        if(api.firstName != null) old.setFirstName(api.firstName);
        if(api.lastName != null) old.setLastName(api.lastName);
        if(api.address != null) old.setAddress(api.address);
        if(api.dateOfBirth != null) old.setDateOfBirth(api.dateOfBirth);
        if(api.mobile != null) old.setMobile(api.mobile);

        try {
            userDao.updateUser(old.getUserId(),old.getAddress(),old.getFirstName(),old.getLastName(),old.getMobile(),
                    old.getDateOfBirth());
            response.id = api.userId;
            response.status = Status.RESPONSE_STATUS_SUCCESS;
            logger.log(Level.INFO, "User updated . Id : " + api.userId);
        } catch (Exception e) {
            e.printStackTrace();

            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = e.getMessage();
            logger.log(Level.WARNING, "User not updated. error : " + e.getMessage());
        }

        return response;
    }
}
