package com.automation.tests.jseExecuter;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JSExecutor2 {

    private WebDriver driver;

    @Test
    public void verifyTitle(){
        String expected = "Practice";
        //we create javascriptexecuter object by casting webdriver object
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //executeScript - this method executes javascript code
        //we provide js code as a String
        // return document.title - javascript code
        // document - represent HTML page;
        //.toString() - to avoid additional
        String actual = (String) js.executeScript("return document.title").toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void click(){
        WebElement link= driver.findElement(By.linkText("Multiple Buttons"));
        //link.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //after "" you can list webelements that will be used
        //as part of your javascript code
        //it's varargs, so you can list 0+
        //arguments - listed after ,
        //use index to get specific webelement
        // WebElement arguments = {element, link, link2};
        //from left - to right
        js.executeScript("arguments[0].click()",link);

        WebElement button6 = driver.findElement(By.id("disappearing_button"));
        js.executeScript("arguments[0].click()",button6);
        BrowserUtils.wait(2);

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),"Now it's gone!");

    }

    @Test
    public void textInputText(){
        driver.findElement(By.linkText("Form Authentication")).click();
        BrowserUtils.wait(2);

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginbtn = driver.findElement(By.id("wooden_spoon"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //to get text from input box - read attribute "value"
        //to enter text - set attribute "value"
        //.setAttribute('value', 'text') - enter some text
        js.executeScript("arguments[0].setAttribute('value', 'tomsmith')", username);
        js.executeScript("arguments[0].setAttribute('value', 'SuperSecretPassword')", password);
        js.executeScript("arguments[0].click()", loginbtn);

        BrowserUtils.wait(4);
        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String subheader = js.executeScript("return document.getElementsByClassName('subheader')[0].textContent").toString();


        Assert.assertEquals(subheader, expected);
    }

    @Test
    public void scrollToElement(){
        BrowserUtils.wait(5);

        //href = link, URL
        WebElement link = driver.findElement(By.linkText("Cybertek School"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true)", link);
    }
   @BeforeMethod
    public void setup(){
       driver = DriverFactory.createADriver("chrome");
       driver.get("http://practice.cybertekschool.com/");
       driver.manage().window().maximize();
   }
   @AfterMethod
    public void tearDawn(){
       BrowserUtils.wait(2);
       driver.quit();
   }
}
