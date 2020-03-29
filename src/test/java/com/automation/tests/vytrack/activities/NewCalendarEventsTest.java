package com.automation.tests.vytrack.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalenderEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewCalendarEventsTest extends AbstractTestBase {

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


}
