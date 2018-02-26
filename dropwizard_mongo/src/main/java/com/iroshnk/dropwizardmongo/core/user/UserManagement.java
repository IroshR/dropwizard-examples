package com.iroshnk.dropwizardmongo.core.user;

import com.iroshnk.dropwizardmongo.core.Management;
import com.iroshnk.dropwizardmongo.persistence.CollectionNames;

/**
 * Created by HP on 9/17/2017.
 */
public class UserManagement extends Management{
    public UserManagement(){
        super(CollectionNames.USER, CollectionNames.USER_HISTORY);
    }
}
