package com.iroshnk.dropwizardmongo.util;

/**
 * Created by HP on 9/16/2017.
 */
public class Status {
    public static int RESPONSE_STATUS_FAIL = -1;
    public static int RESPONSE_STATUS_SUCCESS = 1;
    public static short CREATED = 0;
    public static short PENDING = 1;
    public static short APPROVED = 2;
    public static short CANCELED = 3;
    public static short REVERTED = 4;
    public static short REJECTED = 5;
    public static short SUSPENDED = 6;
    public static short BLACKLISTED = 7;
    public static short DELETED = 8;
}
