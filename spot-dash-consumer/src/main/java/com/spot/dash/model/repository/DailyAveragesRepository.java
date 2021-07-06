package com.spot.dash.model.repository;

import com.spot.dash.model.entity.DailyAverages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DailyAveragesRepository extends MongoRepository<DailyAverages, String> {

    @Query("{ 'analysisDate': ?0 }")
    List<DailyAverages> findByAnalysisDate(String analysisDate);
}
