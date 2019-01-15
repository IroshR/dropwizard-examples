package com.iroshnk.dropwizarddynamo.dao.mapper;

import com.iroshnk.dropwizarddynamo.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by HP on 8/13/2017.
 */
public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("USER_ID"));
        user.setAddress(rs.getString("ADDRESS"));
        user.setCreatedDate(rs.getDate("CREATED_DATE"));
        user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
        user.setEmail(rs.getString("EMAIL"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setMobile(rs.getString("MOBILE"));
        user.setStatus(rs.getInt("STATUS"));
        return user;
    }
}
