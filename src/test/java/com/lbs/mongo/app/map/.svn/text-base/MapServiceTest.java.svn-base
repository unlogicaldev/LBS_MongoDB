package com.lbs.mongo.app.map;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;

import com.lbs.mongo.BaseTest;
import com.lbs.mongo.app.map.entity.Position;
import com.lbs.mongo.app.map.entity.emb.Colum;
import com.lbs.mongo.app.map.entity.emb.Loc;
import com.lbs.mongo.common.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MapServiceTest extends BaseTest{

	@Autowired private MapService mapService;
	@Autowired private MongoTemplate mongoTemplate;
	
	Random oRandom = new Random();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void retriveByLocTest(){
		int flag = 1;
		System.out.println(new Date());
		//127.20234830601646, geolat:37.47723568528637
		//37.566556260416185, long:126.97757739748386
		List<DBObject> list = mapService.retriveByLoc(flag, 37.566556260416185D, 126.97757739748386D);
		System.out.println(new Date() + "!!!"+ list.size());
		for(DBObject o:list){
			System.out.println(o.toString());
		}
		
		//fail();
	}
	
	@Test
	public void randomMove(){
		
		//long time = new Date().getTime();
		DBCollection coll = mongoTemplate.getCollection("position");
		//
		//DBCollection coll = mongoTemplate.getDb().getCollection("position");

		DBObject query = new BasicDBObject();
		long tm = new Date().getTime();
		DBObject loc;
		DBObject oo;
		int cnt = 150000;
		while(true){
			rMove(coll, query);
			rMove(coll, query);
			rMove(coll, query);
			rMove(coll, query);
			rMove(coll, query);
			
			//System.out.println(cnt);
			//cnt --;
			//cnt --;
			//if(new Date().getTime() - tm > 30000) break;
		}
		//System.out.println(new Date().getTime() - tm);
//		query = null;
//		coll = null;
	}

	private void rMove(DBCollection coll, DBObject query) {
		DBObject loc;
		DBObject oo;
		query.put("no", oRandom.nextInt(300000));
		oo = coll.findOne(query);
		loc = (DBObject) oo.get("loc");
		loc.put("geolat", moveLat((Double) loc.get("geolat")));
		loc.put("geolong", moveLng((Double) loc.get("geolong")));
		oo.put("modDate", DateUtil.getToday("yyyyMMddHHmmss"));
		oo.put("loc", loc);
		mapService.movement(coll, oo);
	}
	
	//@Test
	public void saveTest(){
		Position position = makePosition("0");
		
		mapService.save(position);
		
		Iterator<Position> pos = mapService.findPage(0, 10).iterator();
		while(pos.hasNext()){
			System.out.println(pos.next().toString());			
		}
		System.out.println(position.toString());
		
		fail();
	}

	@Test
	public void saveListTest(){
		List<Position> list = new ArrayList<Position>();

		System.out.println(new Date());
		for(int i=0;i<300000;i++){
			list.add(makePosition(i+""));
			if(list.size() == 5000){
				mapService.save(list);
				list = new ArrayList<Position>();
			}
		}
		mapService.save(list);
		System.out.println(new Date());
		
		fail();
	}

	/**
	 * @return
	 */
	private Position makePosition(String i) {
		Colum colum = new Colum(i+1,i+2,i+3,i+4,i+5,
								i+6,i+7,i+8,i+9,i+10,
								i+11,i+12,i+13,i+14,i+15,
								i+16,i+17,i+18,i+19,i+20);
		
		Loc loc = new Loc();
		loc.setGeolong(randomLong());
		loc.setGeolat(randomLat());
		
		Position position = new Position(null,Integer.parseInt(i),"test"+i,1,"20110518194415","20110518194415",colum,loc);
		return position;
	}
	
	
	private Double randomLat(){
		return 37.45869226D + oRandom.nextDouble()*2133556/10000000;
	}
	private Double randomLong(){
		return 126.7383297D + oRandom.nextDouble()*4772191/10000000;
	}
	
	
	private Double moveLat(Double d){
		d = randomDoubleMove(d);
		if(d > 37.59333531324131D) d = randomLat();
		else if(d < 37.53986115648557D) d = randomLat();
		return d;
	}

	private Double randomDoubleMove(Double d) {
		if(oRandom.nextBoolean()){
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
}
