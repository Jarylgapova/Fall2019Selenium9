package com.automation.tests.homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DayTest {

    public WebDriver driver;

    @Test
    public void dayTest(){
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        int i = 0;
        String day= "";

        while (i < 3) {
            BrowserUtils.wait(2);
            // Randomly select a checkbox.
            List<WebElement> options = driver.findElements(By.cssSelector(".gwt-CheckBox>label"));
            Random random = new Random();

            int index = random.nextInt(options.size());
            if (options.get(index).isEnabled()) {
                options.get(index).click();

                day = options.get(index).getText();

                if (day.equals("Friday")) {

                    i++;
                }
                options.get(index).click();
            }
        }

    }


    @Test
    public void currentDay() {
        driver.get("http://practice.cybertekschool.com/dropdown");

        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MMMMM/dd");

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String date1= dateFormat.format(date);

        // Print the Date
        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day= driver.findElement(By.id("day"));

        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);

        String year1 = y.getFirstSelectedOption().getText();
        String month1 = m.getFirstSelectedOption().getText();
        String day1 = d.getFirstSelectedOption().getText();
        String date2 = year1+"/"+month1+"/"+day1;

        Assert.assertEquals(date2,date1, "TEST FAILED TY DURA ");
        System.out.println(date2);
        System.out.println(date1);
    }

    @Test
    public void test3(){
        driver.get("http://practice.cybertekschool.com/dropdown");



    }

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createADriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        driver.quit();
    }

}
