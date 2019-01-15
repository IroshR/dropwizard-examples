package com.iroshnk.dropwizarddynamo.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.gson.Gson;
import com.iroshnk.dropwizarddynamo.model.User;

public class UserDAO {
    Gson gson = null;
    DynamoDBMapper mapper = null;

    public UserDAO(AmazonDynamoDBClient amazonDynamoDBClient) {
        gson = new Gson();
        mapper = new DynamoDBMapper(amazonDynamoDBClient);
    }

    public User getUserByUserName(String userId) {
        User user = mapper.load(User.class, userId);
        return user;
    }

    public boolean addUser(User user) {
        try {
            mapper.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(User user) {
        try {
            mapper.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteUser(String user_id) {
        try {
            User user = mapper.load(User.class, user_id);
            mapper.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
