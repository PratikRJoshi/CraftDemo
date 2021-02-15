package com.example.dataobjects;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class TableResponseFieldObject {
    String name, address, url;
    double rating;
    int zipcode;

}
