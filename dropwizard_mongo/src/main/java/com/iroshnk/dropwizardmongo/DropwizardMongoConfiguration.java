package com.iroshnk.dropwizardmongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by HP on 9/16/2017.
 */
public class DropwizardMongoConfiguration extends Configuration{
    @JsonProperty("mongoDatabase")
    public Map mongoDatabase;

    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration jerseyClient;

    @Valid
    @NotNull
    @JsonProperty("documentation")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
}
