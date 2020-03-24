package com.automation.tests.explicitImplicitWait;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FluentWaitTest {
    private WebDriver driver;


    @Test
    public void fluentWaitTest() {
        driver.get("http://practice.cybertekschool.com/dynamic_loading/6");

        Wait<WebDriver> wait = new FluentWait<>(driver) // dont crete same class name
        .withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(3)).
        ignoring(NoSuchElementException.class).
        ignoring(ElementClickInterceptedException.class);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));
//        WebElement submitBtn = wait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver webDriver) {
//                return driver.findElement(By.xpath("//button[text()='Submit']"));
//            }
//        });

        // Anonymous - class without name
        WebElement submitBtn = wait.until(driver -> driver.findElement(By.xpath("//button[text()='Submit']")));

        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");

        submitBtn.click();
    }
    @BeforeMethod
    public void setup(){
    driver= DriverFactory.createADriver("chrome");

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
