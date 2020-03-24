package com.automation.tests.homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DayTest {

    public WebDriver driver;

    @Test
    public void dayTest(){
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");

        BrowserUtils.wait(2);
        List<WebElement> allCheck  =driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement each : allCheck) {
            if(!each.isSelected()){
                each.click();
            }
        }
    }

    @Test
    public void currentDay(){
        driver.get("http://practice.cybertekschool.com/dropdown");

        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MMMMM/dd");

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String date1= dateFormat.format(date);

        // Print the Date
        String year = driver.findElement(By.xpath("//*[@id=\"year\"]/option[1]")).getText();
        String month = driver.findElement(By.xpath("//*[@id=\"month\"]/option[3]")).getText();
        String day = driver.findElement(By.xpath("//*[@id=\"day\"]/option[23]")).getText();

        String date2 = year+"/"+month+"/"+day;

        Assert.assertEquals(date2,date1, "TEST FAILED TY DURA ");
        System.out.println(date2);
        System.out.println(date1);
    }


    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createADriver("chrome");

    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}
