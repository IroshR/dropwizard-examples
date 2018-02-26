package com.iroshnk.dropwizardmongo.api.request;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by HP on 9/17/2017.
 */
public class StatusUpdateAPI {
    public long primary_id;
    @ApiModelProperty(value = "0 = ADDED, 1 = PENDING, 2 = PUBLISHED, 3 = REMOVED, 4 = HALTED", required = true, position = 0)
    public short status;
}
