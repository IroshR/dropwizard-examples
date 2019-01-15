package com.iroshnk.dropwizarde.core.user;

import com.iroshnk.dropwizarde.api.request.CreateUserRequest;
import com.iroshnk.dropwizarde.api.request.UpdateUserRequest;
import com.iroshnk.dropwizarde.api.response.CreationResponse;
import com.iroshnk.dropwizarde.api.response.UpdateResponse;
import com.iroshnk.dropwizarde.model.User;
import com.iroshnk.dropwizarde.persistence.PersistenceManager;
import com.iroshnk.dropwizarde.util.ModelToApiConverter;
import com.iroshnk.dropwizarde.util.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by HP on 8/11/2017.
 */
public class UserManagement {
    static Logger logger = Logger.getLogger(UserManagement.class.getName());

    public UserManagement(){
    }

    public CreationResponse create(CreateUserRequest api){
        CreationResponse response = new CreationResponse();

        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        User user = new User();
        user.setFirstName(api.firstName);
        user.setLastName(api.lastName);
        user.setAddress(api.address);
        user.setDateOfBirth(api.dateOfBirth);
        user.setEmail(api.email);
        user.setMobile(api.mobile);
        user.setStatus(Status.PENDING);
        user.setCreatedDate(new Date());

        EntityTransaction txn = em.getTransaction();
        txn.begin();
        try {
            em.persist(user);
            txn.commit();
            response.status = Status.RESPONSE_STATUS_SUCCESS;
            response.id = user.getUserId();
            response.data = ModelToApiConverter.convert(user);
            logger.log(Level.INFO, "User created. Id : " + user.getUserId());
        } catch (Exception e) {
            if (txn.isActive()) {
                txn.rollback();
            }
            em.clear();
            e.printStackTrace();

            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = e.getMessage();
            logger.log(Level.WARNING, "User not created. error : " + e.getMessage());
        }

        return response;
    }

    public UpdateResponse update(UpdateUserRequest api){
        UpdateResponse response = new UpdateResponse();

        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        User old = em.find(User.class,api.userId);
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

        EntityTransaction txn = em.getTransaction();
        txn.begin();
        try {
            em.merge(old);
            txn.commit();
            em.refresh(old);
            response.id = old.getUserId();
            response.status = Status.RESPONSE_STATUS_SUCCESS;
            logger.log(Level.INFO, "User updated . Id : " + old.getUserId());
        } catch (Exception e) {
            if (txn.isActive()) {
                txn.rollback();
            }
            em.clear();
            e.printStackTrace();

            response.status = Status.RESPONSE_STATUS_FAIL;
            response.message = e.getMessage();
            logger.log(Level.WARNING, "User not updated. error : " + e.getMessage());
        }

        return response;
    }
}
