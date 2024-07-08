package stepdefinitions.coinstepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

import stepdefinitions.BaseStep;

import java.util.List;

public class HomePageStepDefinitions extends BaseStep {

	public static final Logger LOGGER = LogManager.getLogger(HomePageStepDefinitions.class);

	@When("the user scrolls to the market")
	public void scrolledToMarketSection() {
		LOGGER.info("Scrolling to section: {}", "market");
		PAGES.getHomePage().scrollToPiyasalarHeader();
	}

	@And("the {string} coin is filtered using the search functionality in the market")
	public void coinIsFilteredByUsingSearchSpecInMarket(String coinName) {
		LOGGER.info("Filtering coin by name: {}", coinName.isEmpty() ? "not a specific coin selected" : coinName);
		PAGES.getHomePage().enterCoinName(coinName);
	}

	@And("the number of coins displayed should be {string}")
	public void numberOfCoinsAppearedInTheMarket(String numberOfAppearedCoin) {
		int expectedNumberOfCoin = Integer.parseInt(numberOfAppearedCoin);
		int actualNumberOfCoin = PAGES.getHomePage().getCoinCount();
		LOGGER.info("Expected number of coins: {}, Actual number of coins: {}", expectedNumberOfCoin,
				actualNumberOfCoin);
		Assertions.assertThat(actualNumberOfCoin).isEqualTo(expectedNumberOfCoin);
	}

	@Then("the user should see {string} in the list")
	public void nameOfCoinsAppearedInTheMarketSection(String nameOfCoin) {
		List<String> appearedCoins = PAGES.getHomePage().getCoinNames();
		boolean coinFound = appearedCoins.stream().anyMatch(coin -> coin.contains(nameOfCoin));
		LOGGER.info("Name of coin {}, Coin found: {}, in the list: {}", nameOfCoin, coinFound,
				String.join(", ", appearedCoins));
		Assertions.assertThat(coinFound).isTrue();
	}

}
