package com.stevanus.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CustomWebDriver {
    private static final String GOOGLE_URL = "https://www.google.com";
    private static final String USER_AGENT =
            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public CustomWebDriver() {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.setHeadless(true);
        optionsChrome.addArguments(USER_AGENT); // it for enable headless

        driver =  new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public void switchTab(String tab) {
        driver.switchTo().window(tab);
    }
    public List<String> prepareTwoTabs() {
        driver.get(GOOGLE_URL);
        jsExecutor.executeScript("window.open()");
        return new ArrayList<>(driver.getWindowHandles());
    }
    /**
     * Function for set url
     * @param Url
     */
    public void setUrl(String Url, String tab){
        switchTab(tab);
        driver.get(Url);
    }

    /**
     * Function for getting web list of element find by Xpath element
     * @param findElement
     * @return List of WebElement
     */
    public List<WebElement> getElementListByXpath(String findElement){
        jsExecutor.executeScript("window.scrollBy(0,600)");
        return driver.findElements(By.xpath(findElement));
    }

    /**
     * Function for waiting until element exists.
     * @param element
     */
    public void waitUntilElementVisible(String element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(element)));
    }

    /**
     * Function for scrolling page down in 300 pixels
     */
    public void scroll(){
        jsExecutor.executeScript("window.scrollBy(0,300)");
    }

    /**
     * function getting text value from xpath element
     * @param element
     * @return String
     */
    public String getTextByElement(String element){
        return driver.findElement(By.xpath(element)).getText();
    }

    /**
     * function getting text value from xpath element in an attribute
     * @param element
     * @param attribute
     * @return String
     */
    public String getTextByElementAttribute(String element, String attribute){
        return driver.findElement(By.xpath(element)).getAttribute(attribute);
    }

    public void close(){
        driver.close();
    }
}
