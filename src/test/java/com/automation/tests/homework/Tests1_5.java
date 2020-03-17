package com.automation.tests.homework;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tests1_5 {

    WebDriver driver;

    @Test(description = "Verify that warning message is displayed")
    public void test1(){

        driver.findElement(By.name("birthday")).sendKeys("wrong_dob");
        BrowserUtils.wait(3);
        String exp = "The date of birth is not valid";
        String act = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]")).getText();

        assertEquals(act, exp);

        WebElement message = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]"));
        assertTrue(message.isDisplayed());

    }

    @Test(description = "Verify that following options for programming languages are displayed: c++, java, JavaScript")
    public void test2(){

        WebElement C = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[1]/label"));
        WebElement Java = driver.findElement(By.xpath("//div/div[2]/label"));
        WebElement JavaS = driver.findElement(By.xpath("//div/div[3]/label"));

        assertTrue(C.isDisplayed());
        assertTrue(Java.isDisplayed());
        assertTrue(JavaS.isDisplayed());

    }


    @Test(description = "Verify that warning message is displayed: “first name must be more than 2 and less than 64 characters long")
    public void test3(){

        driver.findElement(By.name("firstname")).sendKeys("K");
        BrowserUtils.wait(3);

        String exp = "first name must be more than 2 and less than 64 characters long";
        String act = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[2]")).getText();
        assertEquals(act,exp);
    }

    @Test(description = "Verify that warning message is displayed: “last name must be more than 2 and less than 64 characters long")
    public void test4(){

        driver.findElement(By.name("lastname")).sendKeys("J");
        BrowserUtils.wait(3);

        String exp = "The last name must be more than 2 and less than 64 characters long";
        String act = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[2]")).getText();
        assertEquals(act,exp);
    }

    @Test
    public void test5(){
        driver.findElement(By.name("firstname")).sendKeys("Tom");
        BrowserUtils.wait(2);

        driver.findElement(By.name("lastname")).sendKeys("Smith");
        BrowserUtils.wait(2);

        driver.findElement(By.name("username")).sendKeys("tomsmith");
        BrowserUtils.wait(2);

        driver.findElement(By.name("email")).sendKeys("tomsmith@gmail.com");
        BrowserUtils.wait(2);

        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        BrowserUtils.wait(2);

        driver.findElement(By.name("phone")).sendKeys("777-777-7777");
        BrowserUtils.wait(2);

        driver.findElement(By.cssSelector("input[value='male']")).click();
        BrowserUtils.wait(2);

        driver.findElement(By.name("birthday")).sendKeys("10/01/1978");
        BrowserUtils.wait(2);

        Select depart = new Select(driver.findElement(By.name("department")));
        depart.selectByVisibleText("Department of Engineering");
        BrowserUtils.wait(2);

        Select job = new Select(driver.findElement(By.name("job_title")));
        job.selectByVisibleText("SDET");
        BrowserUtils.wait(2);

        driver.findElement(By.xpath("//label[text()='Java']/preceding-sibling::input")).click();

        driver.findElement(By.id("wooden_spoon")).click();


        String exp = "You've successfully completed registration!";
        String act = driver.findElement(By.tagName("p")).getText();
        assertEquals(act, exp);

    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("80").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.xpath("//a[.='Registration Form']")).click();
        driver.manage().window().maximize();
        BrowserUtils.wait(3);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

