package com.iroshnk.dropwizardmongo.core.user;

import com.iroshnk.dropwizardmongo.core.Search;
import com.iroshnk.dropwizardmongo.persistence.CollectionNames;

/**
 * Created by HP on 9/17/2017.
 */
public class UserSearch extends Search {
    @Override
    protected String getBaseCollectionName(){
        return CollectionNames.USER;
    }
}
