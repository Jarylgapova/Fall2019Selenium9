package com.automation.tests.homework;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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

        String expected2 = "Thanks for subscribing to practice.cybertekschool.com!";
        String actual2 = driver.findElement(By.id("predmet")).getText();
        assertEquals(actual2,expected2);
    }

    @Test
    public void Test6() throws Exception {
        //Test case #6
        //Step 1. Go to "https://www.tempmailaddress.com/"
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        // Thread.sleep(3);
        String email = driver.findElement(By.id("email")).getText();
        //Step 2. Copy and save email as a string.
        //Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
        driver.navigate().to("http://practice.cybertekschool.com/");
        //Step 4. And click on “Sign Up For Mailing List".
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        //Step 5. Enter any valid name.
        driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("Maggie");
        //Step 6. Enter email from the Step 2.
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        //Step 7. Click Sign Up
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        //Step 8. Verify that following message is displayed:
        String actual = driver.findElement(By.cssSelector("h3[class='subheader']")).getText();
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual, expected);
        //“Thank you for signing up. Click the button below to
        //return to the home page.”
        //Step 9. Navigate back to the “https://
        //www.tempmailaddress.com/”
        //driver.navigate().to("https://www.tempmailaddress.com/");
        driver.navigate().back();    //  ZDES' NADO TAK DELAT POTOMU WTO ESLI TY PROSTO NOVIGATE().TO" ...."
        BrowserUtils.wait(1);// ON BUDET OTKRYVAT ETU STRANICU ZANOVO I TAM UJE DRUGOY EMAIL

        driver.navigate().back();  //NAM NUJNO 3 RAZA BACK SDELAT CHTOBY DOYTI IMENNO DO TOY STRANICY CHTOB IMENNO TOT EMAIL KOTORIY TY ISPOLZOVALA
        BrowserUtils.wait(1);

        driver.navigate().back();
        BrowserUtils.wait(1);

        driver.navigate().refresh();
        BrowserUtils.wait(2);

        //Step 10. Verify that you’ve received an email from
        //“do-not-reply@practice.cybertekschool.com”
//        driver.findElement(By.cssSelector("a[class='navbar-brand']")).click();


        Thread.sleep(5);
        //Step 11. Click on that email to open it.
        Actions actions = new Actions(driver);
        WebElement elementLocator = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]"));
        actions.doubleClick(elementLocator).perform();
        //Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
        String expectedEmail="do-not-reply@practice.cybertekschool.com";
        String actualEmail = driver.findElement(By.id("odesilatel")).getText();

        Assert.assertEquals(actualEmail, expectedEmail);

        //Step 13. Verify that subject is: “Thanks for
        String expected2 = "Thanks for subscribing to practice.cybertekschool.com!";
        String actual2 = driver.findElement(By.id("predmet")).getText();
        assertEquals(actual2, expected2);
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
