package com.example.dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YelpRequest {
    @JsonProperty("limit")
    public int limit;

    @JsonProperty("zipCode")
    public int zipCode;
}
