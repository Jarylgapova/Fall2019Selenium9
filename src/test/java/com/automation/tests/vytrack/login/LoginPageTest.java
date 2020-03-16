package com.automation.tests.vytrack.login;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

//STATIC IMPORT OF ALL ASSERTIONS



public class LoginPageTest {
    private WebDriver driver;
    //https is a secured version of http protocol
    //http - it's hypertext transfer protocol that every single website is using now days
    //https - data encrypted, no chance for hackers to retrieve info
    //http - data as a plain text, very easy to hack it

    private String URL = "https://qa2.vytrack.com/user/login";
    private By usernameBy = By.id("prependedInput");
    private By passwordBy = By.id("prependedInput2");
    private By warningMessage = By.cssSelector("[class='alert alert-error']");

    //CREDENTIALS FOR store manager
    private String username = "storemanager85";
    private String password = "UserUser123";

    @Test(description = "Verify that ")
    public void invalidUserName(){

        driver.findElement(usernameBy).sendKeys("invalidusername");
        driver.findElement(passwordBy).sendKeys("UserUser123", Keys.ENTER);

        BrowserUtils.wait(5);

        WebElement warningElement = driver.findElement(warningMessage);
        assertTrue(warningElement.isDisplayed());

        String expected = "Invalid user name or password.";
        String actual = warningElement.getText();
        assertEquals(actual, expected);


    }
    @Test(description = "")
    public void loginAsStoreManager(){
        driver.findElement(usernameBy).sendKeys(username);
        driver.findElement(passwordBy).sendKeys(password, Keys.ENTER);
        BrowserUtils.wait(3);

        String exp = "Dashboard";
        String act = driver.getTitle();

        assertEquals(act,exp, "Page title is not correct");


    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("80.0").setup();
        driver = new ChromeDriver();
        driver.get("https://qa2.vytrack.com/user/login");
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void tearDown(){
        //if webdriver object alive
        if (driver !=null){
            //close browser, close session
            driver.quit();
            //destroy webdriver object for sure
            driver=null;
        }
    }
}
