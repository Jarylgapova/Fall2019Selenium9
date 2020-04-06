package com.automation.tests.vytrack.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalenderEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class NewCalendarEventsTest extends AbstractTestBase {

    LoginPage loginPage = new LoginPage();
    CalenderEventsPage calendarEventsPage = new CalenderEventsPage();
    /**
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Default owner name should be current user/
     **/
    @Test
    public void defaultOptionsTest(){
        LoginPage login = new LoginPage();
        CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

        login.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();
        Assert.assertEquals(calenderEventsPage.getOwnerName(), calenderEventsPage.getCurrentUserName());

        Assert.assertEquals(calenderEventsPage.getStartDate(), DateTimeUtilities.getCurrentDate("MMM dd, yyyy"));


    }


    /**
     * 35 minutes until 4:05
     * Test Case: Time difference
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Verify that difference between start and end time is 1 hour
     **/
    @Test
    public void timeDifferenceTest(){
        LoginPage login = new LoginPage();
        CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

        login.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();

        String startTime = calenderEventsPage.getStartTime();
        String endTime  = calenderEventsPage.getEndTime();
        String format = "h:m a";

        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime,format);

        Assert.assertEquals(actual, 1, "Time differences is not correct");
    }

    /**
     * Test Case: Verify calendar events table
     * Login as store manager
     * Go to Activities --> Calendar Events
     * And verify that column names displayed:
     * |TITLE            |
     * |CALENDAR         |
     * |START            |
     * |END              |
     * |RECURRENT        |
     * |RECURRENCE       |
     * |INVITATION STATUS|
     */
    @Test
    public void verifyColumnNames(){
        LoginPage login = new LoginPage();
        CalenderEventsPage calenderEventsPage = new CalenderEventsPage();

        login.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END", "RECURRENT", "RECURRENCE", "INVITATION STATUS");

        Assert.assertEquals(calenderEventsPage.getColumnNames(), expected);
    }

    @Test(dataProvider = "calendarEvents")
    public void createCalendarEventTest(String title, String description) {
        //if you have more one test, and 1st pass but others failing,
        //you are getting session id is null exception
        //because driver object was not initialized in time
        //just create page objects inside a test
        LoginPage loginPage = new LoginPage();
        CalenderEventsPage calendarEventsPage = new CalenderEventsPage();

        //only for extent report. To create a test in html report
        test = report.createTest("Create calendar event for " + title);
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.enterCalendarEventTitle(title);
        calendarEventsPage.enterCalendarEventDescription(description);
        calendarEventsPage.clickOnSaveAndClose();

        //verify that calendar event info is correct
        Assert.assertEquals(calendarEventsPage.getGeneralInfoDescriptionText(), description);
        Assert.assertEquals(calendarEventsPage.getGeneralInfoTitleText(), title);

        //for extent report. specify that test passed in report (if all assertions passed)
        test.pass("Calendar event was created successfully!");
    }

    @DataProvider
    public Object[][] calendarEvents() {
        return new Object[][]{
                {"Daily stand-up", "Scrum meeting to provide updates"},
                {"Sprint Review", "Scrum meeting where team discussing previous sprint"},
                {"Sprint Planning", "Scrum meeting where team discussing backlog for following sprint"}
        };
    }

}
