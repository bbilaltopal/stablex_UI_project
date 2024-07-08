package org.stablex.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.stablex.configurations.ConfigurationManager;

public class DriverManager {

	private static final ThreadLocal<WebDriver> THREAD_LOCAL_DRIVER = new ThreadLocal<>();

	private DriverManager() {
		throw new UnsupportedOperationException("You cannot object of utils.DriverManager");
	}

	public static WebDriver getDriver() {
		String browserType = ConfigurationManager.getProperty("browser").toLowerCase();
		WebDriver driver;
		if (THREAD_LOCAL_DRIVER.get() == null) {
			switch (browserType.toLowerCase()) {
				case "firefox" -> {
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.addArguments("--width-1920");
					firefoxOptions.addArguments("--height-1080");
					driver = new FirefoxDriver(firefoxOptions);
				}
				case "edge" -> {
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.addArguments("--start-maximized");
					edgeOptions.addArguments("--ignore-certificate-error");
					driver = new EdgeDriver(edgeOptions);
				}

				default -> {
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--start-maximized"); // Maximize the
																		// window
					chromeOptions.addArguments("--ignore-certificate-errors"); // Ignore
																				// certificate
																				// errors
					chromeOptions.addArguments("--disable-infobars"); // Disable infobars
																		// (e.g., "Chrome
																		// is being
																		// controlled by
																		// automated test
																		// software")
					chromeOptions.addArguments("--disable-extensions"); // Disable
																		// extensions
					chromeOptions.addArguments("--disable-gpu"); // Disable GPU
																	// acceleration
					chromeOptions.addArguments("--disable-notifications"); // Disable
																			// browser
																			// notifications
					chromeOptions.addArguments("--disable-popup-blocking"); // Disable
																			// popup
																			// blocking
					chromeOptions.addArguments("--no-sandbox"); // Bypass OS security
																// model (useful in Linux
																// environments)
					chromeOptions.addArguments("--disable-dev-shm-usage"); // Disable
																			// /dev/shm
																			// usage
																			// (useful in
																			// Linux
																			// environments)
					chromeOptions.addArguments("--disable-web-security"); // Disable web
																			// security
					chromeOptions.addArguments("--disable-features=IsolateOrigins,site-per-process"); // Disable
																										// site
																										// isolation
																										// and
																										// per-process
																										// sandbox
					chromeOptions.addArguments("--no-default-browser-check"); // Skip
																				// default
																				// browser
																				// check
					chromeOptions.addArguments("--disable-default-apps"); // Disable
																			// default app
					driver = new ChromeDriver(chromeOptions);
				}
			}

			THREAD_LOCAL_DRIVER.set(driver);
		}

		return THREAD_LOCAL_DRIVER.get();
	}

	public static void closeDriver() {
		WebDriver driver = DriverManager.getDriver();
		if (driver != null) {
			driver.quit();
			THREAD_LOCAL_DRIVER.remove();
		}
	}

}