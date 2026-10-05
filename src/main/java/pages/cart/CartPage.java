package pages.cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;


import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private Logger log = LogManager.getLogger(CartPage.class);

    private final By checkoutLocator = By.id("checkout");

    public CartPage( WebDriver driver){
        super(driver);
    }


    public WebElement getCheckoutButton(){
        log.info("getting password field");
        return findElement(checkoutLocator);
    }

    public void clickCheckout(){
        getCheckoutButton().click();
        log.info("getting click button");

    }


}
