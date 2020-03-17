package com.automation.tests.homework;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Test7 {

    WebDriver driver;

    @Test(description = " Verify that subject is: “File Uploaded!”")
    public void test7(){
        WebElement file = driver.findElement(By.id("file-upload"));
        file.sendKeys("/Users/Karima/Documents/soft Skills.pages");
        BrowserUtils.wait(1);

        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(1);

        //step 5
        String exp = "File Uploaded!";
        String act = driver.findElement(By.tagName("h3")).getText();
        assertEquals(act, exp, "TEST FAIL");

        // step 6
        String fileName = "soft Skills.pages";
        String expected = driver.findElement(By.id("uploaded-files")).getText();
       assertEquals(fileName, expected,"TEST FAIL");

    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("80").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.xpath("//a[.='File Upload']")).click();
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
