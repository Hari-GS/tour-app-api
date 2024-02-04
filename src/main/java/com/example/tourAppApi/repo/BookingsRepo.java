package com.example.tourAppApi.repo;

import com.example.tourAppApi.model.Bookings;
import com.example.tourAppApi.model.Plans;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingsRepo extends MongoRepository<Bookings, ObjectId> {
    Bookings findByUserName(String userName);
}
