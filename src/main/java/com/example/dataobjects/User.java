package com.example.dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id")
    public int id;

    @JsonProperty("email")
    public String email;
}
