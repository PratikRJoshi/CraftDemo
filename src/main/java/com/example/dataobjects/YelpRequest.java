package com.example.dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;
import lombok.Getter;

@Getter
public class YelpRequest {
    @JsonProperty("limit")
    @Nullable
    public int limit;

    @Nullable
    @JsonProperty("zipcode")
    public int zipcode;

    @JsonProperty("name")
    public String name;
}
