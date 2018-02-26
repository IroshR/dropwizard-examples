package com.iroshnk.dropwizardjdbi.dao;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

/**
 * Created by HP on 8/11/2017.
 */
public class DMLOperations {
    private final DBI dbi;

    public DMLOperations(DBI dbi){
        this.dbi = dbi;
    }

    public void createTables() {
        Handle handle = dbi.open();
        handle.execute("CREATE TABLE IF NOT EXISTS USER (\n" +
                "  USER_ID int(11) NOT NULL auto_increment primary key,\n" +
                "  ADDRESS varchar(255) DEFAULT NULL,\n" +
                "  CREATED_DATE datetime NOT NULL,\n" +
                "  DATE_OF_BIRTH date DEFAULT NULL,\n" +
                "  EMAIL varchar(40) DEFAULT NULL,\n" +
                "  FIRST_NAME varchar(255) DEFAULT NULL,\n" +
                "  LAST_NAME varchar(40) DEFAULT NULL,\n" +
                "  MOBILE varchar(40) NOT NULL,\n" +
                "  STATUS int(11) NOT NULL)");
        handle.close();
    }
}
