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

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
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

        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day= driver.findElement(By.id("day"));

        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);

        BrowserUtils.wait(2);
        Random random = new Random();
        int index = random.nextInt(y.getOptions().size());
        y.selectByIndex(index);
        //y.selectByIndex(new Random().nextInt(y.getOptions().size())); you can do it one line


        int  febDays;
        String y2 = y.getFirstSelectedOption().getText();
        int numOfDays = Integer.parseInt(y2);

        if (numOfDays%4==0){
            febDays=29;
        }else{
            febDays=28;
        }

//febDays =  Integer.parseInt(y.getFirstSelectedOption().getText())%4==0?29:28; you can do it with one line


        List<String> monthList= new ArrayList<>(Arrays.asList(new String[]{"January", "March", "May", "July", "August", "October", "December"}));

        for (int i=0; i<12; i++){

            m.selectByIndex(i);
            if(monthList.contains(m.getFirstSelectedOption().getText())) {
                Assert.assertEquals(d.getOptions().size(), 31);
            }else if(m.getFirstSelectedOption().getText().equals("February")){
                Assert.assertEquals(d.getOptions().size(), febDays);
            }else{
                Assert.assertEquals(d.getOptions().size(), 30);
            }
        }
    }




    @Test
    public void years_months_days(){
        driver.get("http://practice.cybertekschool.com/dropdown");
        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        Select y =new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);
        y.selectByIndex(new Random().nextInt(y.getOptions().size()));
        List<String> months31= new ArrayList<>(Arrays.asList(new String[]{"January", "March", "May", "July", "August", "October", "December"}));
        int febDays;
        febDays =  Integer.parseInt(y.getFirstSelectedOption().getText())%4==0?29:28;

        for(int i =0 ; i<12; i++){
            m.selectByIndex(i);
            if(months31.contains(m.getFirstSelectedOption().getText())) {
                Assert.assertEquals(d.getOptions().size(), 31);
            }else if(m.getFirstSelectedOption().getText().equals("February")){
                Assert.assertEquals(d.getOptions().size(), febDays);
            }else{
                Assert.assertEquals(d.getOptions().size(), 30);
            }


        }

    }

    @Test
    public void departmentSort(){
        driver.get("https://www.amazon.com/");
        String exp = "All";
        String act = driver.findElement(By.xpath("//*[text()='All']")).getText();
        Assert.assertEquals(act,exp);

        driver.findElement(By.id("searchDropdownBox")).click();

        List<WebElement> list = new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();

        boolean AlphabeticOrder = false;
        for(int i=0; i<list.size()-1; i++){
            if(list.get(i).getText().compareTo(list.get(i+1).getText())>0){
                AlphabeticOrder = true;
                System.out.println("alphabetic");
                break;
            }
        }
        Assert.assertTrue(AlphabeticOrder);

    }
    @Test
    public void verifyDropdowns() {

        driver.get("https://chercher.tech/practice/dropdowns");
        // original dropdown
        WebElement originalDropdown = driver.findElement(By.cssSelector("select#first"));
        Select original = new  Select(originalDropdown);
        List<WebElement> originalListElements = original.getOptions();
        List<String> originalList = new ArrayList<String>();
        for (WebElement webElement : originalListElements) {
            originalList.add(webElement.getText());
        }

        // target dropdown
        WebElement targetDropdown = driver.findElement(By.id("order-same"));
        Select target = new  Select(targetDropdown);
        List<WebElement> targetListElements = target.getOptions();
        List<String> targetList = new ArrayList<String>();
        for (WebElement webElement : targetListElements) {
            targetList.add(webElement.getText());
        }
        Assert.assertEquals(originalList, targetList);
    }

    @Test// with List
    public void mainDepart(){
        driver.get("https://www.amazon.com/gp/site-directory");
        List<WebElement> mainDep = driver.findElements(By.tagName("h2"));

        driver.findElement(By.id("searchDropdownBox")).click();
        WebElement allDepart = driver.findElement(By.id("searchDropdownBox"));
        Select allSelect = new Select(allDepart);
        List<WebElement> allDepartElements = allSelect.getOptions();
        List<String> allList = new ArrayList<String>();
        for (WebElement each :allDepartElements) {
            allList.add(each.getText());
        }


        List<WebElement> mainDepartElements = driver.findElements(By.tagName("h2"));
        List<String> listOfMain = new ArrayList<String>();
        for (WebElement webElement :mainDepartElements) {
            listOfMain.add(webElement.getText());
        }

        Assert.assertTrue(allList.containsAll(listOfMain));
    }

    @Test // solution with Set
    public void main_departments(){
        driver.get("https://www.amazon.com/gp/site-directory");
        List<WebElement> mainDepart = driver.findElements(By.tagName("h2"));
        driver.findElement(By.id("searchDropdownBox")).click();

        List<WebElement> allDepart =new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        Set<String > mainDep = new HashSet<String>();
        Set<String > allDep = new HashSet<String>();
        for(WebElement each : mainDepart){
            mainDep.add(each.getText());
        }
        for(WebElement each : allDepart){
            allDep.add(each.getText());
        }


        for(String each : mainDep){
            if(!allDep.contains(each)){
                System.out.println(each);

            }
        }
        Assert.assertTrue(allDep.containsAll(mainDep), "there is not main dep in the All dep");
    }




    @Test
    public void linkTest(){
        driver.get("https://www.w3schools.com/");
        List<WebElement> elements =  driver.findElements(By.tagName("a"));
        for (WebElement each : elements) {
            if(each.isDisplayed()){
                System.out.println(each.getText());
                System.out.println(each.getAttribute("href"));
            }
        }
    }

    @Test
    public void findBrokenLinks(){
        driver.get("https://www.selenium.dev/documentation/en/");
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Total links: " + links.size());

        for (int i=0; i<links.size(); i++){
            String linkUrl = links.get(i).getAttribute("href");

            try {
                URL url = new URL(linkUrl);
                HttpURLConnection httpConnect = (HttpURLConnection)url.openConnection();
                httpConnect.setConnectTimeout(3000);

                httpConnect.connect();
                if(httpConnect.getResponseCode()==200)
                {
                    System.out.println(linkUrl+" - "+httpConnect.getResponseMessage());
                }
                if(httpConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
                {
                    System.out.println(linkUrl+" - "+httpConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
                }
            }catch (Exception e){

            }
        }

    }

    @Test
    public void valid_links() {
        driver.get("https://www.selenium.dev/documentation/en/");
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for(int i=0; i<links.size(); i++){
            String  hrefAtr= links.get(i).getAttribute("");
            try {
                URL url = new URL(hrefAtr);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.connect();
                Assert.assertTrue(httpURLConnection.getResponseCode()==200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void cartTest(){
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.className("nav-input")).click();

        List<WebElement> allPrice = driver.findElements(By.xpath("//span[@class='a-price']/span[@class='a-offscreen']"));
        Random rand = new Random();
        int index = rand.nextInt(allPrice.size());

        String randomProductName = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-medium'])["+index+"]//span[@class='a-size-base-plus a-color-base a-text-normal']")).getText();
        String randomProductPrice = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-medium'])["+index+"]//span[@class='a-price-whole']")).getText() + "."+ driver.findElement(By.xpath("(//div[@class='a-section a-spacing-medium'])["+index+"]//span[@class='a-price-fraction']")).getText();

        WebElement randomElement = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-medium'])["+index+"]"));
        randomElement.click();

        String quantity = driver.findElement(By.xpath("//span[@id='a-autoid-0-announce']//span[@class='a-dropdown-prompt']")).getText();

        String priceOfRandomAfterClick = driver.findElement(By.xpath("//span[contains(@id,'priceblock')]")).getText();
        String nameOfRandomAfterClick = driver.findElement(By.id("productTitle")).getText();

        Assert.assertEquals(quantity,"1");
        Assert.assertTrue(priceOfRandomAfterClick.contains(randomProductPrice));
        Assert.assertEquals(randomProductName,nameOfRandomAfterClick);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Add to Cart']")).isDisplayed());
    }

    @Test
    public void primeTest(){
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon");
        driver.findElement(By.className("nav-input")).click();

        String firstPrime = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]")).getText();

        driver.findElement(By.xpath("//i[@class='a-icon a-icon-prime a-icon-medium']/../div/label/i")).click();

        String nameAfterClick = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/../../../../../..//h2)[1]")).getText();
        Assert.assertEquals(nameAfterClick,firstPrime);
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
