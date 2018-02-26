package com.iroshnk.dropwizarde.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HP on 8/11/2017.
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @TableGenerator(name = "USER_ID_GEN", table = "SEQ_STORE", pkColumnName = "SEQ_NAME", pkColumnValue = "USER.USER_ID", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_ID_GEN")
    @Column(name = "USER_ID", insertable = true, updatable = false, nullable = false)
    private int userId;
    @Column(name = "FIRST_NAME", insertable = true, updatable = true, nullable = true, length = 255)
    private String firstName;
    @Column(name = "LAST_NAME", insertable = true, updatable = true, nullable = true, length = 40)
    private String lastName;
    @Column(name = "DATE_OF_BIRTH", insertable = true, updatable = true, nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "CREATED_DATE", insertable = true, updatable = true, nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "ADDRESS", insertable = true, updatable = true, nullable = true, length = 255)
    private String address;
    @Column(name = "MOBILE", insertable = true, updatable = true, nullable = false, length = 40)
    private String mobile;
    @Column(name = "EMAIL", insertable = true, updatable = true, nullable = true, length = 40)
    private String email;
    @Column(name = "STATUS", insertable = true, updatable = true, nullable = false)
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
