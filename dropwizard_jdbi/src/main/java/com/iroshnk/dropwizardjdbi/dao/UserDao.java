package com.iroshnk.dropwizardjdbi.dao;

import com.iroshnk.dropwizardjdbi.dao.mapper.UserMapper;
import com.iroshnk.dropwizardjdbi.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.Date;

/**
 * Created by HP on 8/11/2017.
 */
public interface UserDao {
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO USER(USER_ID,ADDRESS,CREATED_DATE,DATE_OF_BIRTH,EMAIL,FIRST_NAME,LAST_NAME,MOBILE,STATUS) " +
            "VALUES (null,:address,:create_id,:date_of_birth,:email,:first_name,:last_name,mobile,:status)")
    int createUser(@Bind("address") String address, @Bind("first_name") String firstName, @Bind("status") int status,
                   @Bind("create_id") Date createDate, @Bind("last_name") String lastName, @Bind("mobile") String mobile,
                   @Bind("email") String email, @Bind("date_of_birth") Date dateOfBirth);

    @SqlUpdate("UPDATE USER SET ADDRESS = :address, FIRST_NAME = :first_name, LAST_NAME = :last_name, MOBILE = :mobile, " +
            "DATE_OF_BIRTH = :date_of_birth WHERE USER_ID = :user_id")
    void updateUser(@Bind("user_id") int userId, @Bind("address") String address, @Bind("first_name") String firstName,
                    @Bind("last_name") String lastName, @Bind("mobile") String mobile, @Bind("date_of_birth") Date dateOfBirth);

    @SqlQuery("SELECT * FROM USER WHERE USER_ID = :user_id")
    @Mapper(UserMapper.class)
    User getUserDetails(@Bind("user_id") int userId);
}
