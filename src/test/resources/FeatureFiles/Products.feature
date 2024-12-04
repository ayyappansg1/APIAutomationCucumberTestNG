#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Fake Stores API Automaition-Products Module

  Background: 
    Given url "https://fakestoreapi.com/"

  Scenario: Fetch All Products in JSON Format
    Given path "products"
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then match response.title contains "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
    And response should  match JSON Schema file named "getAllProductsSchema"
    But status should be 404 for invalid endPoint "product"
   
   Scenario: Get a Single Product
    Given path "products/1"
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then matches with response.title contains "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
    And response should  match JSON Schema file named "getSingleProduct"
    But status should be 404 for invalid endPoint "product"
    
   Scenario: Get a Limited Results
    Given path "products" and query Param key is "limit" value is 5
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response JSON should have 5 objects
    Then match response.title contains "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"
    And response should  match JSON Schema file named "getLimitedResults"
    But status should be 404 for invalid endPoint "product"
    
   Scenario: Get a Sorted Results
    Given path "products" and query Param key is "sort" value is "desc"
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response JSON should have 20 objects
    Then match response.title contains "DANVOUY Womens T Shirt Casual Cotton Short"
    And response should  match JSON Schema file named "getSortedProducts"
    But status should be 404 for invalid endPoint "product"
  
    Scenario: Fetch All categories in json format
    Given path "products/categories"
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    And response should  match JSON Schema file named "getCategories"
    But status should be 404 for invalid endPoint "product"
    
    Scenario: Get a Single Category
    Given path "products/category/jewelery"
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then match response.title contains "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet"
    And response should  match JSON Schema file named "getSingleCategory"
    But status should be 404 for invalid endPoint "product"

    Scenario: Add New Product
    Given path "products"
    When method POST with Deserialsation
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then matches with response.title contains "sangar"
    But status should be 404 for invalid endPoint "product"

    Scenario: Update Existing Product
    Given path "products/7"
    When method PUT and payload file named "updateExistingProduct"
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then matches with response.title contains "test product"
    And response should  match JSON Schema file named "updateProduct"
    But status should be 404 for invalid endPoint "product"
    
    Scenario: Delete Existing Product
    Given path "products/6"
    When method DELETE
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then matches with response.title contains "Solid Gold Petite Micropave "
    And response should  match JSON Schema file named "deleteProduct"
    But status should be 404 for invalid endPoint "product"
    
    Scenario: User Login
    Given path "auth/login"
    When method POST and payload file named "userLogin"
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then matches with response.token should not be empty or null
    And response should  match JSON Schema file named "userLogin"
    But status should be 404 for invalid endPoint "product"
    
    @smoke
    Scenario: Get All Categories
    Given path "carts"
    When method GET
    And status 200
		And status message is "OK"
		And response time less than 2000 ms
		And response is not null
    Then match response should be equal to file named "allCategoriesExpectedJSON"
    But status should be 404 for invalid endPoint "product"