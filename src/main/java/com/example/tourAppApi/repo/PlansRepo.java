package com.example.tourAppApi.repo;


import com.example.tourAppApi.model.Model;
import com.example.tourAppApi.model.Plans;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlansRepo extends MongoRepository<Plans, ObjectId> {
    Plans findByBusNumber(int busNumber);
}
