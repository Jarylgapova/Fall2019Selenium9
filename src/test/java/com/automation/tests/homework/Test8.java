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

public class Test8 {

    WebDriver driver;
    @Test
    public void test8(){
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
        BrowserUtils.wait(2);
        String expected = "You selected: United States of America";
        String actual = driver.findElement(By.id("result")).getText();
        assertEquals(actual,expected, "test fail");
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("80").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.xpath("//a[.='Autocomplete']")).click();
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
