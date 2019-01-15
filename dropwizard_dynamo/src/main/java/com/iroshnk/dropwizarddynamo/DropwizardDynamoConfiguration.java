package com.iroshnk.dropwizarddynamo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardDynamoConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("accessKey")
    public String accessKey;
    @Valid
    @NotNull
    @JsonProperty("secretAccessKey")
    public String secretAccessKey;
    @Valid
    @NotNull
    @JsonProperty("endpoint")
    public String endpoint;
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration jerseyClient;
}
