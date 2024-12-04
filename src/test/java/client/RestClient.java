package client;


import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Local helper class contains all Reusable action able methods with Wait
 * mechanism added
 */
public class RestClient {
	private static final Logger logger = LogManager.getLogger(RestClient.class);
	private volatile static RestClient instance;
	private RestClient() {}
	public static RestClient getInstance() {
		synchronized (RestClient.class) {
			if(instance==null) {
				instance=new RestClient();
			}
		}
		return instance;
	}
	
	public Response doGet(String baseURI,String basePath,boolean log,String contentType,Map<String,String> params) {
		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(log,contentType,params);
			return getResponse("GET",request,basePath);
		}
		return null;
	}
	public Response doPost(String baseURI,String basePath,boolean log,String contentType,Map<String,String> params,String body) {
		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(log,contentType,params);
			if(body!=null)request.body(body);
			return getResponse("POST",request,basePath);
		}
		return null;
	}
	public Response doPut(String baseURI,String basePath,boolean log,String contentType,Map<String,String> params,String body) {
		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(log,contentType,params);
			if(body!=null)request.body(body);
			return getResponse("PUT",request,basePath);
		}
		return null;
	}
	public Response doDelete(String baseURI,String basePath,boolean log,String contentType,Map<String,String> params,String body) {
		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(log,contentType,params);
			if(body!=null)request.body(body);
			return getResponse("DELETE",request,basePath);
		}
		return null;
	}
	private Response getResponse(String httpMethod,RequestSpecification request,String basePath) {
		Response response=null;
		switch (httpMethod) {
		case "GET":
			response=request.get(basePath);
			break;
		case "POST":
			response=request.post(basePath);
			break;
		case "PUT":
			response=request.put(basePath);
			break;
		case "DELETE":
			response=request.delete(basePath);
			break;
		default:
			break;
		}
		return response;
	}
	private RequestSpecification createRequest(boolean log,String contentType,Map<String,String> params) {
        RequestSpecification request;
		if(log) {
			request=RestAssured.given().log().all();
		}else {
			request=RestAssured.given();
		}
		if(contentType!=null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}else if(contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}else if(contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			}else if(contentType.equalsIgnoreCase("multipart")) {
				request.multiPart(new File(""));
			}
		}
		if(params!=null) {
			request.queryParams(params);
		}
		return request;
	}
	private boolean setBaseURI(String baseURI) {
		if(baseURI==null||baseURI.isEmpty()) return false;
		try {
			RestAssured.baseURI=baseURI;
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
