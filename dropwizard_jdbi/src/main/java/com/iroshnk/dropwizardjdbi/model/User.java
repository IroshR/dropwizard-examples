package com.iroshnk.dropwizardjdbi.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by HP on 8/11/2017.
 */
@ApiModel
public class User {
    @ApiModelProperty(value = "generated primary key", required = false)
    private int userId;
    @ApiModelProperty(value = "First Name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last Name", required = false)
    private String lastName;
    @ApiModelProperty(value = "Date Of birth", required = false)
    private Date dateOfBirth;
    @ApiModelProperty(value = "Create Date", required = true)
    private Date createdDate;
    @ApiModelProperty(value = "Address", required = false)
    private String address;
    @ApiModelProperty(value = "Mobile", required = true)
    private String mobile;
    @ApiModelProperty(value = "Email", required = false)
    private String email;
    @ApiModelProperty(value = "Status", required = true)
    private int status;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
