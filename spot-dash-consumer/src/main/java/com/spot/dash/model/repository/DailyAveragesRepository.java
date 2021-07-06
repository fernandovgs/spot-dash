package com.spot.dash.model.repository;

import com.spot.dash.model.entity.DailyAverages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DailyAveragesRepository extends MongoRepository<DailyAverages, String> {
}
