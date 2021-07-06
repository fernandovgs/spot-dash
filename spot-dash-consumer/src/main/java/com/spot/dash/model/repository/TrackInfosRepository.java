package com.spot.dash.model.repository;

import com.spot.dash.model.entity.TrackInfos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackInfosRepository extends MongoRepository<TrackInfos, String> {
}
