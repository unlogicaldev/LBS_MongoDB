package com.lbs.mongo.app.map;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lbs.mongo.app.map.entity.Position;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface MapService {

	/**
	 * @param position
	 * @return
	 */
	public Position save(Position position);
	
	/**
	 * @param list
	 * @return
	 */
	public List<Position> save(List<Position> list);
	
	/**
	 * @param page
	 * @param pageCnt
	 * @return
	 */
	public Page<Position> findPage(int page, int pageCnt);
	
	/**
	 * search near location
	 * @param flag
	 * @param geolong
	 * @param geolat
	 * @return
	 */
	public List<DBObject> retriveByLoc(int flag, Double geolong, Double geolat);
	
	/**
	 * Async method
	 */
	void randomMovement();
	
	void movement(DBCollection coll, DBObject oo);
}
