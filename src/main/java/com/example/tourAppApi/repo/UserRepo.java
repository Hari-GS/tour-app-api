package com.example.tourAppApi.repo;

import com.example.tourAppApi.model.Plans;
import com.example.tourAppApi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByUserId(int userId);
}
