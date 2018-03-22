Feature: API GET requests

Scenario: Verify the email from response body content 
   Given The apis are up and running for "https://jsonplaceholder.typicode.com/"
   When A user performs a get request to "comments/1"   
   Then The response has a status code 200         
   And User verifies the response body content as given below
      |Key          | Value                 |
      |email				|	Eliseo@gardner.biz    |
      
      
Scenario: Verify the number of users in response body content 
	 Given The apis are up and running for "https://jsonplaceholder.typicode.com/"
   When A user performs a get request to "users"   
   Then The response has a status code 200
   And The response the number of user returned should be 10