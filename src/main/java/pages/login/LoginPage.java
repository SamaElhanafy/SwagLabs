package pages.login;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class LoginPage extends BasePage{
    private Logger log = LogManager.getLogger(LoginPage.class);

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public WebElement getUsernameField() {
        log.info("getting username field");
        return findElement(usernameField);
    }
    public WebElement getPasswordField() {
        log.info("getting password field");
        return findElement(passwordField);
    }
    public WebElement getLoginBtn() {
        log.info("getting login button");
        return findElement(loginBtn);
    }

    public void enterUsername(String username){
        getUsernameField().sendKeys(username);
        log.debug("Enter username: ()*", username);


    }
    public void enterPassword(String password){
        getPasswordField().sendKeys(password);
        log.debug("Enter password: ()*", password);

    }
    public void clickLogin(){
        getLoginBtn().click();
        log.info("getting click button");

    }
}
