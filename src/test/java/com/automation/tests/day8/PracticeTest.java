package com.automation.tests.day8;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PracticeTest {
    private WebDriver driver;
    @BeforeMethod
    public void setup(){
        //setup webdriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void teardown(){
        //close browser and destroy webdriver object
        driver.quit();
    }

    @Test
    public void loginTest(){
        driver.get("http://practice.cybertekschool.com/login");
        BrowserUtils.wait(3);

        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.id("wooden_spoon")).click();

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.tagName("h4")).getText();
        BrowserUtils.wait(3);

        Assert.assertTrue(expected.equals(actual));
        BrowserUtils.wait(4);


    }

    @Test
    public void forgetPasswordTest(){
        driver.get("http://practice.cybertekschool.com/forgot_password");
        BrowserUtils.wait(3);

        driver.findElement(By.name("email")).sendKeys("tomsmith@gmail.com");
        driver.findElement(By.id("form_submit")).click();
        BrowserUtils.wait(3);
        String exp = "Your e-mail's been sent!";
        String act = driver.findElement(By.tagName("h4")).getText();

        Assert.assertTrue(exp.equals(act));
        BrowserUtils.wait(3);


    }

    @Test
    public void checkboxTest(){
        driver.get("http://practice.cybertekschool.com/checkboxes");
        BrowserUtils.wait(3);

        List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
        checkboxes.get(0).click();
        Assert.assertTrue(checkboxes.get(0).isSelected());
    }







}
