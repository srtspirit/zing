package com.zingcrm.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.zingcrm.service.GeocodingService;

@Service
public class GeocodingServiceImpl implements GeocodingService {

	private static Logger LOG = Logger.getLogger(GeocodingServiceImpl.class
			.getName());
	
	@Override
	public String searchLoc(String searchText) {
		
		WebConversation wc = new WebConversation();
		JSONObject temp=null;
		String searchLocations="",latitude="",longitude="";
		Double lat,lng;
		try {
			WebRequest locations = new PostMethodWebRequest(
					"http://maps.googleapis.com/maps/api/geocode/json?address="
							+ URLEncoder.encode(searchText, "UTF-8") + "&sensor=false");
			
			searchLocations = wc.getResponse(locations).getText();
			JSONObject jsonResult = new JSONObject(searchLocations);
			JSONArray jsonarr = (JSONArray) jsonResult.get("results");
			temp = (JSONObject)((JSONObject) jsonarr.get(0)).get("geometry");
			temp=(JSONObject)temp.get("location");
			lat= (Double)temp.get("lat");
			lng= (Double)temp.get("lng");
			  latitude = Double.toString(lat);
			  longitude = Double.toString(lng);
		} catch (UnsupportedEncodingException e) {
			return 0+","+0;
			
		} catch (IOException e) {
			return 0+","+0;
		}catch (SAXException e) {
			return 0+","+0;
		} catch (Exception e) {
			return 0+","+0;
		}
		return latitude+","+longitude;
	}

	

}