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
    private List<Product> productList;
    private int count = 100;

    private CustomWebDriver webDriver;

    /**
     * Function for populate link of product in category handphone and tablet pages.
     * It will stored in List of String.
     * Those link will used for getting product detail.
     * @throws IOException
     */
    public void getLinkProductList() throws IOException {
        webDriver = new CustomWebDriver();
        webDriver.setUrl(TOKOPEDIA_URL);
        linkProductList = new ArrayList<>();
        try{
            while(linkProductList.size()!=count) {
                List<WebElement> productList = webDriver.getElementListByXpath(XPATH_PRODUCT_LIST);

                for (WebElement webElement : productList) {
                    try {
                        String path = webElement.findElement(By.tagName("a")).getAttribute("href");
                        if(!linkProductList.contains(path)) linkProductList.add(path); // for avoid duplication
                    }catch (Exception e){
                        continue;
                    }
                    if(linkProductList.size()%10==0){
                        System.out.println(linkProductList.size());
                    }


                    if(linkProductList.size()==count){
                        break;
                    }
                }
            }
            System.out.println(linkProductList.size());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.close();
        }
    }

    /**
     * Function for getting product detail and store it into list of product object.
     */
    public void getProductAttribute(){
        webDriver = new CustomWebDriver();
        productList = new ArrayList<>();
        Product product;
        for(String linkProduct:linkProductList){
            webDriver.setUrl(linkProduct);
            System.out.println(linkProduct);
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

            productList.add(product);

        }
        webDriver.close();
    }

    /**
     * Function for returning list of product object
     * @return list of product object
     */
    public List<Product> getProductList(){
        return productList;
    }
}
