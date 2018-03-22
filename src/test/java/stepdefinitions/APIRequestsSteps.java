package stepdefinitions;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class APIRequestsSteps {

	String subUrl;
	Response response;

	@Given("^The apis are up and running for \"([^\"]*)\"$")
	public void setBaseURI(String uri) {
		baseURI = uri;
	}

	@When("^A user performs a get request to \"([^\"]*)\"$")
	public void performGETRequestWithGivenSubURL(String url) {
		this.subUrl = url;
		response = when().get(subUrl);
	}

	@Then("^The response has a status code (\\d+)$")
	public void the_response_has_a_status_code(int statusCode) {
		Assert.assertEquals(statusCode, response.getStatusCode());
	}

	@And("^User verifies the response body content as given below$")
	public void verifyResponseBodyContent(DataTable table) {
		ValidatableResponse validate = response.then();
		String key = null, value = null;
		for (Map<String, String> map : table.asMaps(String.class, String.class)) {
			key = map.get("Key");
			value = map.get("Value");
			validate.body(key, equalTo(value.trim()));
		}
	}

	@Then("^The response the number of user returned should be (\\d+)$")
	public void verifyObjectCountInArray(int count) {
		ValidatableResponse validate = response.then();
		validate.body("it.size()", is(count));

	}

}
