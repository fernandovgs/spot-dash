package com.spot.dash.model.repository;

import com.spot.dash.model.entity.TrackInfos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TrackInfosRepository extends MongoRepository<TrackInfos, String> {

    @Query("{ 'receivedDate': ?0 }")
    List<TrackInfos> findByReceivedDate(String receivedDate);
}
