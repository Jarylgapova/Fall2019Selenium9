package com.automation.tests.homework;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class Test9_12 {

    WebDriver driver;
    @Test(description = "This page returned a 200 status code.")
    public void test9(){

        driver.findElement(By.xpath("//a[.='200']")).click();
        BrowserUtils.wait(1);
        String expected = "This page returned a 200 status code.";

        String actual = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p")).getText();
        if (actual.contains("This page returned a 200 status code.")){
            actual= "This page returned a 200 status code.";
        }else{
            System.out.println("fail");
        }
        assertEquals(actual, expected, "TEST FAIL");
    }
    @Test(description = "This page returned a 301 status code.")
    public void test10(){

        driver.findElement(By.xpath("//a[.='301']")).click();
        BrowserUtils.wait(1);

        String expected = "This page returned a 301 status code.";
        String actual = driver.findElement(By.tagName("p")).getText();
        if (actual.contains("This page returned a 301 status code.")){
            actual= "This page returned a 301 status code.";
        }else{
            System.out.println("fail");
        }
        assertEquals(actual, expected, "TEST FAIL");
    }

    @Test(description = "This page returned a 404 status code.")
    public void test11(){

        driver.findElement(By.xpath("//a[.='404']")).click();
        BrowserUtils.wait(1);

        String expected = "This page returned a 404 status code.";
        String actual = driver.findElement(By.tagName("p")).getText();
        if (actual.contains("This page returned a 404 status code.")){
            actual= "This page returned a 404 status code.";
        }else{
            System.out.println("fail");
        }
        assertEquals(actual, expected, "TEST FAIL");
    }
    @Test(description = "This page returned a 500 status code.")
    public void test12(){

        driver.findElement(By.xpath("//a[.='500']")).click();
        BrowserUtils.wait(1);

        String expected = "This page returned a 500 status code.";
        String actual = driver.findElement(By.tagName("p")).getText();
        if (actual.contains("This page returned a 500 status code.")){
            actual= "This page returned a 500 status code.";
        }else{
            System.out.println("fail");
        }
        assertEquals(actual, expected, "TEST FAIL");
    }




    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("80").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.xpath("//a[.='Status Codes']")).click();
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
