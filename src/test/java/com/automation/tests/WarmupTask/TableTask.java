package com.automation.tests.WarmupTask;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TableTask {
    private WebDriver driver;

    /**
     * Go to http://practice.cybertekschool.com/tables
     * Click on "Last name" column name
     * Verify that table is sorted by last name in alphabetic order.
     *
     * The Java String compareTo() method is used for comparing two strings lexicographically.
     * Each character of both the strings is converted into a Unicode value for comparison.
     * If both the strings are equal then this method returns 0 else it returns positive or negative value.
     * The result is positive if the first string is lexicographically greater than
     * the second string else the result would be negative.
     *
     *  This method is coming from Comparable interface.
     *  If you want ot be able to sort collection of some class
     *  you need to implement this interface
     *  and override compareTo method
     *
     "a".compareTo("b") = -1
     61 - 62 = -1
     a is before b
     "a".compareTo("a")
     61 - 61 = 0
     0 means 2 strings are equals
     *
     */
    @Test
    public void test() {

        driver = DriverFactory.createADriver("chrome");
        driver.get("http://practice.cybertekschool.com/tables");
        driver.findElement(By.xpath("//table[1]//*[text()='Last Name']")).click();
        BrowserUtils.wait(2);

        List<WebElement> column = driver.findElements(By.xpath("//table[1]//tbody//td[1]"));
        for(int i=0; i<column.size()-1; i++){
            String value = column.get(i).getText();
            String nextValue = column.get(i+1).getText();
            Assert.assertTrue(value.compareTo(nextValue)<=0);
        }
        driver.quit();

    }
}
