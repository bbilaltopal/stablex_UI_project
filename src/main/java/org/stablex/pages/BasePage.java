package org.stablex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stablex.utils.DriverManager;

import java.time.Duration;

public class BasePage {

	protected final WebDriver driver;

	protected final WebDriverWait wait;


	public BasePage() {
		this.driver = DriverManager.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Initialize WebElements
		PageFactory.initElements(driver, this);

		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

}
