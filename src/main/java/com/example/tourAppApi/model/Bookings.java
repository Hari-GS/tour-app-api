package com.example.tourAppApi.model;

import lombok.Data;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EntityScan
@Document(collection = "bookings")
@Data
public class Bookings {
    private String userName;
    private int userId;
    private int busNumber;
    private int bookedSeats;
    private String date;
    private String location;
}
