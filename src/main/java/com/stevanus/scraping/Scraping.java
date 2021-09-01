package com.stevanus.scraping;

import com.stevanus.model.Product;
import com.stevanus.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraping {

    private static final String TOKOPEDIA_URL = "https://www.tokopedia.com/p/handphone-tablet";

    private static final String XPATH_PRODUCT_LIST = "//div[@data-testid='master-product-card']/div";
    private static final String XPATH_PRODUCT_NAME = "//h1[@data-testid='lblPDPDetailProductName']";
    private static final String XPATH_PRODUCT_DESCRIPTION = "//div[@data-testid='lblPDPDescriptionProduk']";
    private static final String XPATH_PRODUCT_IMG_LINK = "//div[@data-testid='PDPImageMain']//img";
    private static final String XPATH_PRODUCT_PRICE = "//*[@data-testid='lblPDPDetailProductPrice']";
    private static final String XPATH_PRODUCT_RATING = "//span[@data-testid='lblPDPDetailProductRatingNumber']";
    private static final String XPATH_MERCHANT_NAME = "//*[@data-testid='llbPDPFooterShopName']//h2";

    private List<String> linkProductList;
    private List<Product> products;
    private int count = 100;

    private CustomWebDriver webDriver;

    /**
     * Function for populate link of product in category handphone and tablet pages.
     * It will stored in List of String.
     * Those link will used for getting product detail.
     *
     */
    public void getLinkProductList() {
        webDriver = new CustomWebDriver();
        List<String> tabs = webDriver.prepareTwoTabs();
        webDriver.setUrl(TOKOPEDIA_URL, tabs.get(0));
        linkProductList = new ArrayList<>();
        Product product;
        products = new ArrayList<>();
        try{
            while(products.size()!=count) {
                List<WebElement> productList = webDriver.getElementListByXpath(XPATH_PRODUCT_LIST);

                for (WebElement webElement : productList) {
                    try {
                        String path = webElement.findElement(By.tagName("a")).getAttribute("href");
                        if(!linkProductList.contains(path))
                            webDriver.setUrl(path,tabs.get(1));
                            System.out.println(path);
                            webDriver.scroll();
                            webDriver.waitUntilElementVisible(XPATH_PRODUCT_NAME);
                            String name = webDriver.getTextByElement(XPATH_PRODUCT_NAME);
                            String description = webDriver.getTextByElement(XPATH_PRODUCT_DESCRIPTION);
                            String imageLink = webDriver.getTextByElementAttribute(XPATH_PRODUCT_IMG_LINK,"src");
                            String price = webDriver.getTextByElement(XPATH_PRODUCT_PRICE).replace("Rp","").replace(".","");
                            webDriver.waitUntilElementVisible(XPATH_PRODUCT_RATING);
                            String rating = webDriver.getTextByElement(XPATH_PRODUCT_RATING);
                            String merchant = webDriver.getTextByElement(XPATH_MERCHANT_NAME);

                            product = new Product(name, description, imageLink, price, rating, merchant);

                            products.add(product);
                            linkProductList.add(path); // for avoid duplication
                    }catch (Exception e){
                        webDriver.switchTab(tabs.get(0));
                        continue;
                    }
                    if(products.size()%10==0){
                        System.out.println(products.size());
                    }


                    if(products.size()==count){
                        break;
                    }
                    webDriver.switchTab(tabs.get(0));
                }
            }
            System.out.println(products.size());
        }catch (Exception e){
            e.printStackTrace();
            webDriver.switchTab(tabs.get(0));
        }finally {
            webDriver.close();
        }
    }


    /**
     * Function for returning list of product object
     * @return list of product object
     */
    public List<Product> getProductList(){
        return products;
    }
}
