package com.lbs.mongo.common.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lbs.mongo.app.map.MapService;

@Service("lbsBatch")
public class LbsBatch {

	@Autowired private MapService mapService;
	
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch(){
//		mapService.randomMovement();
//	}
//	
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch1(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch2(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch3(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch4(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch5(){
//		mapService.randomMovement();
//	}
//	
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch6(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch7(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch8(){
//		mapService.randomMovement();
//	}
//
//	@Scheduled(cron="*/1 * * * * *")
//	public void movementBatch9(){
//		mapService.randomMovement();
//	}
}
