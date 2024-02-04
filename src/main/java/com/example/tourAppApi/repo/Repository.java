package com.example.tourAppApi.repo;

import com.example.tourAppApi.model.Model;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Model, ObjectId> {
    Model findByUsername(String username);
}
