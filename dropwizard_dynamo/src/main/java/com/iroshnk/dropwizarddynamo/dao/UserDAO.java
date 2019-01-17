package com.iroshnk.dropwizarddynamo.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.google.gson.Gson;
import com.iroshnk.dropwizarddynamo.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    Gson gson = null;
    DynamoDBMapper mapper = null;
    String TABLE_NAME = "USER";

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

    public User getUserByEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("email = :email ")
                .withExpressionAttributeValues(eav);

        List<User> users = mapper.query(User.class, queryExpression);

        if(users != null && !users.isEmpty()){
            return users.get(0);
        }

        return null;
    }

}
