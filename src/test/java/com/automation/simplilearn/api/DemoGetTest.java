package com.automation.simplilearn.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class DemoGetTest {
	
	@Test
	public void getWeatherDetails(){
		RestAssured.baseURI = "https://newsapi.org/v2/top-headlines";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"?country=in&apiKey=fd6aba04ddd64985be54bd270e15e303");
		String responseBody = response.getBody().asString();
		
		System.out.println("Response body is :"+responseBody);
		
		JsonPath jsonResponse = response.jsonPath();
		ArrayList totalResult = jsonResponse.get("articles");
		
		System.out.println("Number of articles are " + totalResult.size());
		LinkedHashMap<Integer, Object> map = new LinkedHashMap<Integer, Object>();
		try{
			for(int i =0; i< totalResult.size(); i++){
				
				map.put(i, totalResult.get(i));
			}
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
		
		System.out.println(map.size());
		for(int i =0; i< map.size(); i++){
			HashMap<String, Object> articleMap = (HashMap<String, Object>) map.get(i);
			System.out.println("Title "+ i + " is :"+articleMap.get("publishedAt"));
			System.out.println();
		}
		
	}

}
