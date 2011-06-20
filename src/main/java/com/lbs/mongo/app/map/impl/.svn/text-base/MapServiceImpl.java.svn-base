package com.lbs.mongo.app.map.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.lbs.mongo.app.map.MapService;
import com.lbs.mongo.app.map.entity.Position;
import com.lbs.mongo.common.base.AbstractBaseService;
import com.lbs.mongo.common.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Service("mapService")
public class MapServiceImpl extends AbstractBaseService<MapService> implements MapService{

	private static int mGroup = 0, uGroup = 0;
	@Autowired private PositionRepository positionRepository;
	
	@Override
	public Page<Position> findPage(int page, int pageCnt) {
		return positionRepository.findAll(new PageRequest(page,pageCnt));
	}

	@Override
	public Position save(Position position) {		
		return positionRepository.save(position);
	}

	@Override
	public List<Position> save(List<Position> list) {
		return positionRepository.save(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DBObject> retriveByLoc(int flag, Double geolong, Double geolat) {
		Object[] location;
		DBObject cmd,query;
		query = new BasicDBObject();
		query.put("flag", flag);
		
		location = new Object[]{ geolong,geolat };
		cmd = new BasicDBObject();
		cmd.put("geoNear", "position");
		cmd.put("near", location);
		cmd.put("num", 30); 					//결과의갯	
		cmd.put("distanceMultiplier", 1.609344*1000*1000);	//거리의 단위
		cmd.put("spherical", true);			//don't mesure distance.
		cmd.put("query", query);
		//CommandResult cr = mongoTemplate.executeCommand(cmd);
		
		CommandResult cr = mongoTemplate.getDb().command(cmd);
		
		return (List<DBObject>)cr.get("results");
	}
	

	@Async
	public void randomMovement(){
		int cnt =300;
		//long time = new Date().getTime();
		DBCollection coll = mongoTemplate.getCollection("position");
		//
		//DBCollection coll = mongoTemplate.getDb().getCollection("position");
        DBObject query = new BasicDBObject();
		
		while(cnt > 1){
			movement(coll, query);
			cnt--;
			mGroup ++;
		}
		
		query = null;
		coll = null;

		log.warn("Tread Group Cnt : {} {}", mGroup, Thread.activeCount());
		/*
		while(cnt > 0){
			int no = new Random().nextInt(300000);
			Position pos = positionRepository.findByNo(no);
			if(pos != null){
				Loc loc = pos.getLoc();
				loc.setGeolat(moveLat(loc.getGeolat()));
				loc.setGeolong(moveLng(loc.getGeolong()));
				pos.setModDate(DateUtil.getToday("yyyyMMddHHmmss"));
				pos.setLoc(loc);
				positionRepository.save(pos);
			}
			cnt--;
		}
		*/
		mGroup --;
		//if(mGroup == 99) mGroup = 0;
		//else mGroup ++;
	}

	@Async
	public void movement(DBCollection coll, DBObject oo) {
		if(mGroup > 60) return;
		mGroup ++;
//		DBObject loc;
//		DBObject oo;
//		query.put("no", new Random().nextInt(300000));
//		oo = coll.findOne(query);
//		loc = (DBObject) oo.get("loc");
//		loc.put("geolat", moveLat((Double) loc.get("geolat")));
//		loc.put("geolong", moveLng((Double) loc.get("geolong")));
//		oo.put("modDate", DateUtil.getToday("yyyyMMddHHmmss"));
//		oo.put("loc", loc);
		coll.save(oo);
		uGroup ++;
//		if(coll.save(oo).getError() == null){
//
//			uGroup ++;
//		}else{
//
//			System.out.println("!!!!!!!!!!!!!!!!!");
//		}
		if(Math.round(uGroup/1000)*1000 == uGroup)	System.out.println(new Date() + ":" + uGroup);
		mGroup --;
		
	}
	
	
	
	private Double moveLat(Double d){
		d = randomDoubleMove(d);
		if(d > 37.59333531324131D) d = randomLat();
		else if(d < 37.53986115648557D) d = randomLat();
		return d;
	}

	private Double randomDoubleMove(Double d) {
		if(new Random().nextBoolean()){
			d = d+ 0.001D;
		}else{
			d = d- 0.001D;
		}
		return d;
	}
	private Double moveLng(Double d){
		d = randomDoubleMove(d);
		if(d>127.03736383642581D) d = randomLong();
		else if(d < 126.9187458249512D) d = randomLong();
		return d;
	}
	
	private Double randomLat(){
		return 37.45869226D + new Random().nextDouble()*2133556/10000000;
	}
	private Double randomLong(){
		return 126.7383297D + new Random().nextDouble()*4772191/10000000;
	}

}
