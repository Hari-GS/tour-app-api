package com.example.tourAppApi.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId objectId;
    private int userId;
    private String userName;
    private boolean isBooked;
    private int bookedSeats;
    private int busNumber;

    public User() {
    }
}
