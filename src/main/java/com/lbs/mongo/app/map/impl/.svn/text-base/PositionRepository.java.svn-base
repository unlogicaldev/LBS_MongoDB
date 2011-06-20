package com.lbs.mongo.app.map.impl;

import org.bson.types.ObjectId;
import org.springframework.data.document.mongodb.repository.MongoRepository;

import com.lbs.mongo.app.map.entity.Position;

public interface PositionRepository extends MongoRepository<Position, ObjectId> {

	Position findByNo(int no);
	
	Position findByName(String name);
}