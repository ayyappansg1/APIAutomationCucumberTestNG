package stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;

import com.github.javafaker.Faker;

import baseClass.BaseClass;
import client.RestClient;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddNewProduct;
import utils.CommonUtils;

/**
 * This is a Step Definitions Class where All Steps has implemented codes
 */
public class Step_Definitions extends BaseClass {
	private static final Logger logger = LogManager.getLogger(Step_Definitions.class);
	private final CommonUtils utils = new CommonUtils();
//	Properties cartProp=utils.initProperties("Cart");
//	Properties productsProp=utils.initProperties("Products");
//	Properties usersProp=utils.initProperties("Users");
	String baseURI;
	RequestSpecification reqSpec;
	Response response;

	@Given("url {string}")
	public void url(String uri) {
		baseURI = uri;
		reqSpec = RestAssured.given().log().all();
		reqSpec.baseUri(uri);
	}

	@Given("path {string}")
	public void path(String path) {
		reqSpec.basePath(path);
	}

	@When("method GET")
	public void method_get() {
		response = reqSpec.get();
	}

	@When("status {int}")
	public void status(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), int1);
	}

	@When("status message is {string}")
	public void status_message_is(String statusMessage) {
		Assert.assertTrue(response.getStatusLine().contains(statusMessage), "Status line not contains OK");
	}

	@When("response time less than {int} ms")
	public void response_time_less_than_ms(Integer int1) {
		Assert.assertTrue(response.getTime() <= (long) int1, "Response time is not less than 2000 ms");
	}

	@When("response is not null")
	public void response_is_not_null() {
		Assert.assertNotNull(response.asString());
	}

	@Then("match response.title contains {string}")
	public void match_response_title_contains(String string) {
		String titleValue = (String) response.jsonPath().get("[0].title");
		Assert.assertTrue(titleValue.contains(string), "JSOn value not equals to expected text");
	}

	@Then("matches with response.title contains {string}")
	public void matches_with_response_title_contains(String string) {
		String titleValue = (String) response.jsonPath().get("title");
		Assert.assertTrue(titleValue.contains(string), "JSOn value not equals to expected text");
	}

	@Then("status should be {int} for invalid endPoint {string}")
	public void status_should_be_for_invalid_end_point(Integer int1, String string) {
		Response doGet = RestClient.getInstance().doGet(baseURI, string, true, "JSON", null);
		logger.info("Negative case response is :" + doGet.asString());
		Assert.assertEquals(doGet.statusCode(), int1, "Negative case failed here,status code is not 404");
	}

	@Then("response should  match JSON Schema file named {string}")
	public void response_should_match_json_schema(String fileName) {
		response.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONSchemas/" + fileName + ".json"));
	}

	@When("response JSON should have {int} objects")
	public void response_json_should_have_objects(Integer int1) {
		int size = response.jsonPath().getList("$").size();
		Assert.assertEquals(size, int1,"Size not matches");
	}
	@Given("path {string} and query Param key is {string} value is {int}")
	public void path_and_query_param_key_is_value_is(String path, String key, Integer value) {
		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
		map.put(key, value);
		reqSpec.basePath(path).queryParams(map);
	}
	@Given("path {string} and query Param key is {string} value is {string}")
	public void path_and_query_param_key_is_value_is(String path, String key, String value) {
		Map<String,String> map=new LinkedHashMap<String,String>();
		map.put(key, value);
		reqSpec.basePath(path).queryParams(map);
	}
	@When("method POST with Deserialsation")
	public void method_post_with_Deserialsation() throws JsonProcessingException {
		Faker faker=new Faker();
		AddNewProduct addProduct=new AddNewProduct();
		addProduct.setTitle("sangar");
		addProduct.setPrice(203.2);
		addProduct.setImage(faker.company().url());
		addProduct.setDescription("SAngar Boy bro");
		addProduct.setCategory("human");
		String serialisedJSON = CommonUtils.getSerialisedJSON(addProduct);
		logger.info("JSON is:"+serialisedJSON);
		response = reqSpec
				.header("Content-Type","application/json")
				.body(serialisedJSON)
				.post();
		logger.info("response is :"+response.asPrettyString());
	}
	@When("method PUT and payload file named {string}")
	public void method_put_and_payload_file_named(String string) throws IOException {
		response = reqSpec
				.header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("src/test/resources/Payload/"+string+".json")))).put();
		logger.info("response is :"+response.asPrettyString());
	}
	@When("method DELETE")
	public void method_delete() {
		response = reqSpec.delete();
	}
	@When("method POST and payload file named {string}")
	public void method_post_and_payload_file_named(String string) throws IOException {
		response =reqSpec.header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("src/test/resources/Payload/"+string+".json"))))
		.post();
		logger.info("response is :"+response.asPrettyString());
	}
	@Then("matches with response.token should not be empty or null")
	public void matches_with_response_token_should_not_be_empty_or_null() {
		Assert.assertTrue(response.jsonPath().get("token")!=null,"token is null here");
	}
	@Then("match response should be equal to file named {string}")
	public void match_response_should_be_equal_to_file_named(String string) throws IOException, JSONException {
		logger.info("Response is :"+response.asString());
		String expectedJSONFileAsString = CommonUtils.getExpectedJSONFileAsString(string);
		JSONAssert.assertEquals(expectedJSONFileAsString, response.asString(), true);
	}
}
