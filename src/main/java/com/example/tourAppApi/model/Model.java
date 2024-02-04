package com.example.tourAppApi.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "signups")
@Data
public class Model {
    @Id
    private ObjectId objectId;
    private String username;
    private int userId;
    private String password;
    private String city;
    private boolean isBooked;
}
