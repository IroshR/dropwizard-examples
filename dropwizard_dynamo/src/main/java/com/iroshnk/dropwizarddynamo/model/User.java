package com.iroshnk.dropwizarddynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;


@ApiModel
@DynamoDBDocument
@DynamoDBTable(tableName = "USER")
public class User {

    @DynamoDBHashKey(attributeName = "user_id")
    @DynamoDBAutoGeneratedKey
    @ApiModelProperty(value = "generated primary key", required = false)
    private String userId;
    @DynamoDBAttribute(attributeName = "first_name")
    @ApiModelProperty(value = "First Name", required = true)
    private String firstName;
    @DynamoDBAttribute(attributeName = "last_name")
    @ApiModelProperty(value = "Last Name", required = false)
    private String lastName;
    @DynamoDBAttribute(attributeName = "date_of_birth")
    @ApiModelProperty(value = "Date Of birth", required = false)
    private String dateOfBirth;
    @DynamoDBAttribute(attributeName = "created_date")
    @ApiModelProperty(value = "Create Date", required = true)
    private String createdDate;
    @DynamoDBAttribute(attributeName = "address")
    @ApiModelProperty(value = "Address", required = false)
    private String address;
    @DynamoDBAttribute(attributeName = "mobile")
    @ApiModelProperty(value = "Mobile", required = true)
    private String mobile;
    @DynamoDBAttribute(attributeName = "email")
    @ApiModelProperty(value = "Email", required = false)
    private String email;
    @DynamoDBAttribute(attributeName = "status")
    @ApiModelProperty(value = "Status", required = true)
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
