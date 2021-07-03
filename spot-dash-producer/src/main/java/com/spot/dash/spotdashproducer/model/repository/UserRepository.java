package com.spot.dash.spotdashproducer.model.repository;

import com.spot.dash.spotdashproducer.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
