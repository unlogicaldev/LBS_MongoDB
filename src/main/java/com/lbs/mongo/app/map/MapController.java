package com.lbs.mongo.app.map;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbs.mongo.common.base.AbstractBaseController;

@Controller
@RequestMapping(value="/map")
public class MapController extends AbstractBaseController<MapController>{
	
	@Autowired private MapService mapService;
	
	/**
	 * map 초기화면
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String map(Model model) {	
		
		if(log.isDebugEnabled()){
			log.debug("MapController Start1");
			log.debug("MapController Start1");
			log.debug("MapController Start1");
		}
		return "map/main";
	}
	
	/**
	 * Geo Searching
	 * @param flag
	 * @param geolong
	 * @param geolat
	 * @param model
	 * @return
	 */
	@RequestMapping(value="search/geo")
	public @ResponseBody Model searchGeo(@RequestParam(required=false,defaultValue="1") int flag,
						   @RequestParam(required=false,defaultValue="") Double geolong,
						   @RequestParam(required=false,defaultValue="") Double geolat,
						   Model model){
		if(log.isDebugEnabled()){
			log.debug("lat:{}, long:{}",geolat,geolong);
		}
		long time = new Date().getTime();
		model.addAttribute("list", mapService.retriveByLoc(flag, geolong, geolat));
		log.warn("time:{}",new Date().getTime() - time);
		return model;
	}
	

}
