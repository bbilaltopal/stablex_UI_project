package org.stablex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.stablex.utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Home Page of the application.
 */
public class HomePage extends BasePage {

	@FindBy(xpath = "//h2[text()='Piyasalar']")
	private WebElement piyasalarHeader;

	@FindBy(xpath = "//input[@placeholder='Ara...']")
	private WebElement searchInputField;

	@FindBy(css = ".max-h-64 .ml-2")
	private List<WebElement> coinElements;

	/**
	 * Scrolls to the 'Piyasalar' header section on the home page.
	 */
	public void scrollToPiyasalarHeader() {
		BrowserUtils.scrollToElement(piyasalarHeader);
	}

	/**
	 * Enters the name of a coin into the search input field.
	 * @param coinName the name of the coin to search for
	 */
	public void enterCoinName(String coinName) {
		searchInputField.sendKeys(coinName);
	}

	/**
	 * Retrieves the number of coins displayed on the page.
	 * @return the number of coins displayed
	 */
	public int getCoinCount() {
		return coinElements.size();
	}

	/**
	 * Retrieves the names of the coins displayed on the page.
	 * @return a list of coin names
	 */
	public List<String> getCoinNames() {
		List<String> coinNames = new ArrayList<>();
		for (WebElement coinElement : coinElements) {
			coinNames.add(coinElement.getText());
		}
		return coinNames;
	}

}
