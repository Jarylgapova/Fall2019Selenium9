package com.automation.tests.practice;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationForm {
    private String URL = "http://practice.cybertekschool.com/registration_form";
    private WebDriver driver;


    @Test
    public void test1(){

        driver.findElement(By.name("firstname")).sendKeys("Karima");
        BrowserUtils.wait(2);
        driver.findElement(By.name("lastname")).sendKeys("Jarylgapova");
        BrowserUtils.wait(2);
        driver.findElement(By.name("username")).sendKeys("supersdet");
        BrowserUtils.wait(2);
        driver.findElement(By.name("email")).sendKeys("email@cybertekschool.com");
        BrowserUtils.wait(2);
        driver.findElement(By.name("password")).sendKeys("Karima001");
        BrowserUtils.wait(2);
        driver.findElement(By.name("phone")).sendKeys("777-777-7777");
        BrowserUtils.wait(2);
        driver.findElement(By.name("birthday")).sendKeys("10/04/1990");
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("input[value='female']")).click();
        BrowserUtils.wait(4);
        driver.findElement(By.xpath("//select[@name=\"department\"]//option[1]")).click();
        BrowserUtils.wait(4);
        driver.findElement(By.xpath("//select[@name=\"job_title\"]//option[5]")).click();
        BrowserUtils.wait(4);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        BrowserUtils.wait(4);
        String expected ="Well done!";
        String actual=driver.findElement(By.xpath("//h4[@class='alert-heading']")).getText();
        BrowserUtils.wait(4);
        Assert.assertEquals(actual,expected);

        BrowserUtils.wait(2);
    }

    @BeforeMethod
    public void setup(){

        WebDriverManager.chromedriver().version("80").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }
}
