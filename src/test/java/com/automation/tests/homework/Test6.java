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

import java.util.List;

import static org.testng.Assert.assertEquals;

public class Test6 {

    WebDriver driver;
    private String URL1 = "https://www.tempmailaddress.com";
    private String URL2 = "http://practice.cybertekschool.com/";

    @Test
    public void test6(){

        String email = driver.findElement(By.className("animace")).getText();
        driver.navigate().to(URL2);
        BrowserUtils.wait(2);

        driver.findElement(By.xpath("//a[text()='Sign Up For Mailing List']")).click();
        driver.findElement(By.name("full_name")).sendKeys("Mister Twister");
        BrowserUtils.wait(1);
        driver.findElement(By.name("email")).sendKeys(email);
        BrowserUtils.wait(1);
        driver.findElement(By.name("wooden_spoon")).click();

        BrowserUtils.wait(1);

        String exp = "Thank you for signing up. Click the button below to return to the home page.";
        String act = driver.findElement(By.name("signup_message")).getText();

        assertEquals(act,exp, "Test fail!");

        driver.navigate().back();
        BrowserUtils.wait(1);

        driver.navigate().back();
        BrowserUtils.wait(1);

        driver.navigate().back();
        BrowserUtils.wait(1);

        driver.navigate().refresh();
        BrowserUtils.wait(2);

        List<WebElement> mails = driver.findElements(By.xpath("//tr[contains(@class,'hidden')]"));
        String expected = "do-not-reply@practice.cybertekschool.com";
        for (int i=0;i<mails.size();i++){
            if(mails.get(i).getText().contains(expected)){
                mails.get(i).click();
                break;
            }
        }
        String actual = driver.findElement(By.xpath("//span[@id='odesilatel']")).getText();
        assertEquals(expected,actual,"TestFail !");

        BrowserUtils.wait(1);
        List<WebElement> mailText = driver.findElements(By.xpath("//html/body/br"));
        for(WebElement each : mailText){
            if(each.getText().contains("Cybertek Support")){
                System.out.println("Test Pass !!!");
            }else {
                System.out.println("Test Fail !!!");
            }
        }
    }


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("80").setup();
        driver = new ChromeDriver();
        driver.get(URL1);
        driver.manage().window().maximize();
        BrowserUtils.wait(3);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
