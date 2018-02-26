package com.iroshnk.dropwizarde;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by HP on 8/11/2017.
 */
public class DropwizardEConfiguration extends Configuration {
    @JsonProperty("database")
    public Map database;
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration jerseyClient;
}
