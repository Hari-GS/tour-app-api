package com.example.tourAppApi.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EntityScan
@Document(collection = "buses")
@Data
public class Plans {
    @Id
    private ObjectId id;
    private int busNumber;
    private int numberOfDays;
    private int availableSeats;
    private String date;
    private String location;
}
