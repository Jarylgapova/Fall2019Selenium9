package com.automation.tests.day5;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckBox {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().version("80.0").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/checkboxes");
        BrowserUtils.wait(2);

        //<input type="checkbox" checked="">
        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        checkBoxes.get(0).click(); // click on first checkbox
        BrowserUtils.wait(2);

        checkBoxes.get(1).click(); // click on the second checkbox
        BrowserUtils.wait(2);
        driver.quit();
    }
}
