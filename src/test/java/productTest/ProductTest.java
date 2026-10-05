package productTest;

import baseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.product.ProductPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductTest extends BaseTest {

    @Test
    public void addToCart() {

        LoginPage login = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        login.enterUsername(configHandler.getValue("username"));
        login.enterPassword(configHandler.getValue("password"));
        login.clickLogin();

        List<String> addedIds = productPage.addSelectedProducts();

        softAssert.assertTrue(addedIds.size() == 5);
        softAssert.assertAll();

        productPage.getShoppingCartBadge().click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");
    }

    @Test
    public void checkCartageIconNumber(){

        LoginPage login = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        login.enterUsername(configHandler.getValue("username"));
        login.enterPassword(configHandler.getValue("password"));
        login.clickLogin();

        softAssert.assertTrue(productPage.cartIconLogic());
        softAssert.assertAll();
    }

    @Test
    public void checkRemoveIsDisplayed() throws InterruptedException{

        LoginPage login = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        login.enterUsername(configHandler.getValue("username"));
        login.enterPassword(configHandler.getValue("password"));
        login.clickLogin();

        List<String> substrings = new ArrayList<>(Arrays.asList(
                jsonFileManager.getValue("product1").toString().toLowerCase(),
                jsonFileManager.getValue("product2").toString().toLowerCase(),
                jsonFileManager.getValue("product3").toString().toLowerCase(),
                jsonFileManager.getValue("product4").toString().toLowerCase()
                )) {};
        
        List<String> linksIds = new ArrayList<>();

        for (String sub: substrings){
            List<WebElement> elements = productPage.getAllProducts();
            for (WebElement el: elements){
                String productName = el.findElement(By.className("inventory_item_name")).getText();
                if (productName.toLowerCase().contains(sub)){
                    el.findElement(By.tagName("button")).click();
                    linksIds.add(el.findElement(By.tagName("a")).getAttribute("id"));
                }
            }
        }
        for (String linkId: linksIds){
            driver.findElement(By.id(linkId)).click();
            Thread.sleep(1000);
            WebElement removeBtn = driver.findElement(By.id("remove"));
            softAssert.assertTrue(removeBtn.isDisplayed());
            driver.navigate().back();
            Thread.sleep(1000);
        }

        softAssert.assertAll();
    }

    @Test
    public void filterProducts() throws InterruptedException{

        LoginPage login = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        login.enterUsername(configHandler.getValue("username"));
        login.enterPassword(configHandler.getValue("password"));
        login.clickLogin();

        WebElement dropdown;
        Select select;

        for (int i = 0 ; i < 4 ; i++){
            dropdown = productPage.getFilterButton();
            select = new Select(dropdown);
            select.selectByIndex(i);
            softAssert.assertTrue(productPage.checkFilter(i));
            Thread.sleep(2000);
        }

        Thread.sleep(3000);
        softAssert.assertAll();

    }
}




