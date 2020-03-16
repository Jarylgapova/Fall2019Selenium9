package com.automation.tests.practiceShort;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LocatorsCSS {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createADriver("chrome");
        driver.get("http://practice.cybertekschool.com/registration_form");
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Karima");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Jarylgapova");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("email@cybertekschool.com");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("supersdet");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("Karima001");
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("777-777-7777");
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("10/04/1990");
        driver.findElement(By.cssSelector("input[value='female']")).click();



        Select select = new Select(driver.findElement(By.cssSelector("select[name='job_title']")));
        select.deselectByVisibleText("SDET");

       // Select select2 = new Select(driver.findElement(By.cssSelector("select[value='DE']")));
        //select.deselectByVisibleText("Department of Engineering");


        driver.findElement(By.id("wooden_spoon")).click();
        BrowserUtils.wait(2);
        driver.close();


    }
}
