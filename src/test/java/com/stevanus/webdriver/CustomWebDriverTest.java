package com.stevanus.webdriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomWebDriverTest {
    private CustomWebDriver customWebDriver;

    @Before
    public void setUp(){
        customWebDriver = new CustomWebDriver();
    }

    @Test
    public void testGetTextFromElement(){
        String url = "https://www.tokopedia.com/foomee/foomee-cc25-chargers-1usb5v2-1a-ns11-1m-bagged-data-cable-micro";
        customWebDriver.setUrl(url);
        customWebDriver.waitUntilElementVisible("//h1[@data-testid='lblPDPDetailProductName']");
        String name = customWebDriver.getTextByElement("//h1[@data-testid='lblPDPDetailProductName']");

        Assert.assertEquals("FOOMEE CC25 chargers 1USB5v2.1A + NS11 1M bagged data cable MICRO",name);
    }

    @After
    public void close(){
        customWebDriver.close();
    }
}
