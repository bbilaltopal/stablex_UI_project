package org.stablex.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class BrowserUtils {

    /**
     Here is some methods that I use frequently to avoid code repetition
     */


    /**
     * Captures a screenshot of the current browser window and saves it with a unique
     * name.
     *
     * @param name the name of the screenshot
     * @return the file path of the captured screenshot
     */
    public static String getScreenshot(String name) {
        // Adding date and time to the screenshot name to make it unique
        name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;
        String path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
        TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * Moves the mouse pointer to the specified web element.
     *
     * @param element the web element to move the mouse pointer to
     */
    public static void moveToElement(WebElement element) {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(element).build().perform();
    }

    /**
     * Navigates to a browser window with the specified title.
     *
     * @param targetTitle the title of the target window
     */
    public static void navigateToWindow(String targetTitle) {
        String currentWindow = DriverManager.getDriver().getWindowHandle();
        for (String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);
            if (DriverManager.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        DriverManager.getDriver().switchTo().window(currentWindow);
    }

    /**
     * Switches to a grandchild window.
     */
    public static void switchToGrandChildWindow() {
        Set<String> windows = DriverManager.getDriver().getWindowHandles();
        Iterator<String> iterations = windows.iterator();
        String parentWindow = iterations.next();
        String childWindow = iterations.next();
        String grandChildindow = iterations.next();
        DriverManager.getDriver().switchTo().window(grandChildindow);
    }

    /**
     * Switches to a popup window.
     */
    public static void switchToPopUpWindow() {
        Set<String> windows = DriverManager.getDriver().getWindowHandles();
        Iterator<String> iterations = windows.iterator();
        String parentWindow = iterations.next();
        String childWindow = iterations.next();
        DriverManager.getDriver().switchTo().window(childWindow);
    }

    /**
     * Pauses the execution for the specified number of seconds.
     *
     * @param secs the number of seconds to wait
     */
    public static void wait(double secs) {
        try {
            Thread.sleep(1000 * (long) secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * (long) secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrolls down using the PAGE_DOWN key a specified number of times.
     *
     * @param times Number of times to press PAGE_DOWN
     */
    public static void scrollDownWithPageDownButton(int times) {
        Actions actions = new Actions(DriverManager.getDriver());
        for (int i = 0; i < times; i++) {
            actions.keyDown(Keys.PAGE_DOWN).build().perform(); // Press PAGE_DOWN key
        }
    }

    /**
     * Scrolls up using the PAGE_UP key a specified number of times.
     *
     * @param times Number of times to press PAGE_UP
     */
    public static void scrollUpWithPageUpButton(int times) {
        Actions actions = new Actions(DriverManager.getDriver());
        for (int i = 0; i < times; i++) {
            actions.keyDown(Keys.PAGE_UP).build().perform(); // Press PAGE_UP key
        }
    }

    /**
     * Scrolls down using JavaScript by a specified offset.
     *
     * @param xOffset Horizontal scroll offset
     * @param yOffset Vertical scroll offset
     */
    public static void scrollDownWithJavaScript(int xOffset, int yOffset) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(" + xOffset + "," + yOffset + ");"); // Scroll window by offset
    }

    /**
     * Clicks on a specified WebElement.
     *
     * @param element WebElement to click on
     */
    public static void clickOnElement(WebElement element) {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(element).click().perform(); // Move to element and click
    }

    /**
     * Executes JavaScript with a specified script on a WebElement.
     *
     * @param script  JavaScript code to execute
     * @param element WebElement to execute script on
     */
    public static void executeJavaScript(String script, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript(script, element); // Execute JavaScript with provided script on element
    }

    /**
     * Scrolls to bring a specified WebElement into view.
     *
     * @param element WebElement to scroll to
     */
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element); // Scroll to element
    }

    /**
     * Sets the value of an element found by a specified locator using JavaScript.
     *
     * @param locator CSS selector or XPath to locate the element
     * @param value   Value to set in the element
     */
    public static void setElementValueByLocator(String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        jsExecutor.executeScript("document.querySelector('" + locator + "').value='" + value + "'"); // Set element value using locator
    }
}
