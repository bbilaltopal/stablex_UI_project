package stepdefinitions;

import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.stablex.configurations.ConfigurationManager;

public class BasicStepDefinitions extends BaseStep {

	private static final Logger LOGGER = LogManager.getLogger(BasicStepDefinitions.class);

	@Given("the user navigates to the base URL")
	public void navigation_to_the_base_url() {
		DRIVER.get(ConfigurationManager.getProperty("baseURL"));
		LOGGER.info("Navigated to the baseURL: " + ConfigurationManager.getProperty("baseURL"));
	}

}
