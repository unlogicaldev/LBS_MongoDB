/*
*	javascript for LBS-MongoDB google maps v3
*	v0.1
*/
var browserSupportFlag;
var map, userMarker, mapClickCnt = 0;
userMarker = new Object();
var markers = [];
var markers1 = [];
var infowindow;
var initialLocation;

	$(document.createElement('script')).attr('src',"http://maps.google.com/maps/api/js?sensor=false&language=ko&callback=mapsLoaded").appendTo('head');
	$(document.createElement('script')).attr('src',"http://code.google.com/apis/gears/gears_init.js").appendTo('head');

function mapsLoaded(){
	createMap("map");
}
var timeEvent;
function searchPosition(geolong,geolat){
	$.ajax({
		   method: "POST",
		   url: "/mongo/map/search/geo",
		   data: {geolong:geolong, geolat:geolat, flag:1},
		   success: function(data){
			   addMarkerByList(data.list);

				if(timeEvent) clearTimeout(timeEvent);
				timeEvent = setTimeout(function(){ searchPosition(geolong,geolat); },1000);
		   },
		   error:function(){
			   if(timeEvent) clearTimeout(timeEvent);
				timeEvent = setTimeout(function(){ searchPosition(geolong,geolat); },1000);
		   },
		   dataType:"json"
		});
}

function addMarkerByList(list){

	clearMarker();
	var txt = "";
	$.each(list,function(i,s){
		makeMarker(new google.maps.LatLng(s.obj.loc.geolat, s.obj.loc.geolong), null, s.obj.name);
		txt += makePost(s);
	});
	$("#geo_result").html(txt);
}

function makeMarker(pos, image, name){
	var marker = new google.maps.Marker({
					position: pos,
					map: map,
					icon: image,
					title: name
					//animation: google.maps.Animation.DROP,
				  });
	
	markers.push(marker); 
	return marker;
 }

function makeMarker1(pos, image, name){
	var marker = new google.maps.Marker({
					position: pos,
					map: map,
					icon: image,
					title: name
					//animation: google.maps.Animation.DROP,
				  });
	
	markers1.push(marker); 
	return marker;
 }

function clearMarker() { 
    for(j=0; j<markers.length; j++){ 
    	markers[j].setMap(null); 
      } 
} 
function clearMarker1() { 
    for(j=0; j<markers1.length; j++){ 
    	markers1[j].setMap(null); 
      } 
} 

function makePost(s){
	var obj = s.obj;
	return '<div class="fs-recpost"><div class="span-3 last"><div class="fs-rectitle"><a href="#" rel="bookmark" title="Pathology">'+obj.name+'('+s.dis+'m)'+'</a></div><div class="fs-recdate">'+parseDateFormatYYYYMMDD(obj.modDate)+'움직임</div></div></div><div class="clear"></div>';
}
function parseDateFormatYYYYMMDD(d){
	d = d+"";
	if(d){
		return d.substring(0,4)+"-"+d.substring(4,6)+"-"+d.substring(6,8)+" "+d.substring(8,10)+":"+d.substring(10,12)+":"+d.substring(12,14);
	}
}
function createMap(mapId){
	infowindow = new google.maps.InfoWindow();
	browserSupportFlag =  new Boolean();
	
	var myOptions = {
		zoom: 18,
		navigationControl: true,
		mapTypeControl: false,
		scaleControl: false,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	  };
	map = new google.maps.Map(document.getElementById(mapId), myOptions);
	map.setCenter(new google.maps.LatLng(37.566535,126.977969));
	
	  

	  var infoWindow = new google.maps.InfoWindow();
	google.maps.event.addListener(map, 'click', function(event) {
	    //var html = 'The LatLng value is: ' + event.latLng + ' at zoom level ' + map.getZoom();
	    //infoWindow.setContent(html);
	    //infoWindow.setPosition(event.latLng);
	    //infoWindow.open(map);
		clearMarker1();
		makeMarker1(event.latLng, "http://code.google.com/apis/maps/documentation/javascript/examples/images/beachflag.png", "Click");
	    searchPosition(event.latLng.lng(),event.latLng.lat());
	  });
	
	requestGoogleGears();
}
function requestGoogleGears(){
	  // Try W3C Geolocation method (Preferred)
	if(navigator.geolocation) {
	  browserSupportFlag = true;
	  navigator.geolocation.getCurrentPosition(function(position) {
	    initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
	    contentString = "Location found using W3C standard";
	    //alert(initialLocation);
	    //map.setCenter(initialLocation);
	  }, function() {
	    handleNoGeolocation(browserSupportFlag);
	  });
	} else if (google.gears) {
	  // Try Google Gears Geolocation
	  browserSupportFlag = true;
	  var geo = google.gears.factory.create('beta.geolocation');
	  geo.getCurrentPosition(function(position) {
	    initialLocation = new google.maps.LatLng(position.latitude,position.longitude);
	    contentString = "Location found using Google Gears";
	   // alert(initialLocation);
	   //map.setCenter(initialLocation);
	  }, function() {
	    handleNoGeolocation(browserSupportFlag);
	  });
	} else {
	  // Browser doesn't support Geolocation
	  browserSupportFlag = false;
	  initialLocation = new google.maps.LatLng(37.566535,126.977969);
	  //alert(initialLocation);
	  handleNoGeolocation(browserSupportFlag);
	}
}
function handleNoGeolocation(errorFlag) {
	  map.setCenter(initialLocation);
}