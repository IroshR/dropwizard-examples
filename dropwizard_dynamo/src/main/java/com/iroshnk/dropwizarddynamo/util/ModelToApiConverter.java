package com.iroshnk.dropwizarddynamo.util;

import com.iroshnk.dropwizarddynamo.api.UserApi;
import com.iroshnk.dropwizarddynamo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 8/12/2017.
 */
public class ModelToApiConverter {
    //user
    public static UserApi convert(User user){
        UserApi api = new UserApi();
        api.userId = user.getUserId();
        api.firstName = user.getFirstName();
        api.lastName = user.getLastName();
        api.dateOfBirth = user.getDateOfBirth();
        api.createdDate = user.getCreatedDate();
        api.address = user.getAddress();
        api.mobile = user.getMobile();
        api.email = user.getEmail();
        api.status = user.getStatus();

        return api;
    }

    public static List<UserApi> convertUsers(List<User> users){
        List<UserApi> userApis = new ArrayList<>(users.size());
        for(User user : users){
            userApis.add(convert(user));
        }

        return userApis;
    }
}
