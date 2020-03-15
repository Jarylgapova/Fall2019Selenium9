package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTestPractice {
    public static void main(String[] args) {

        String expected = "cba";
        String toRevers = "abc";
        String actual = reverseString(toRevers);

        verifyEquals(expected, actual);

    }
    @Test(description = "Verify if method can reverse a string")
    public void test(){
        String expected = "elppa";
        String actual = reverseString("apple");
        Assert.assertEquals(actual, expected);

    }

    public static boolean verifyEquals(String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
            return true;
        } else {
            System.out.println("Test failed!!!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            return false;
        }
    }

    public static String reverseString(String str){
        String reverse = "";
        for (int i = str.length()-1; i >=0; i--) {
            reverse+= str.charAt(i);
        }
        return reverse;
    }
}
