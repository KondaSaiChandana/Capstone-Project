package com.CapstoneProject.CapstoneProject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class accountdetails extends baseclass {
	@Test
    public void testLoginAndViewAccountDetails() throws IOException, InterruptedException {
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        test.log(Status.INFO, "Clicked on Sign In link");


        // Enter username and password
        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[1]/input"));
        usernameField.clear();
        usernameField.sendKeys("Jpetstore");
        test.log(Status.INFO, "Entered username");


        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[2]/input"));
        passwordField.clear();
        passwordField.sendKeys("jpet@123");
        test.log(Status.INFO, "Entered password");


        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/div/button"));
        loginButton.click();
        takeScreenshot("JPetStore_AfterLogin");
        test.log(Status.PASS, "Logged in successfully");



        // Navigate to the account details page
        try {
            WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
            myAccountLink.click();
            Thread.sleep(2000);
            test.log(Status.INFO, "Navigated to My Account page");

            // Scroll to account details section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 250)");
            js.executeScript("window.scrollBy(250, 450)");
            takeScreenshot("JPetStore_AfterLogin");

            Thread.sleep(2000);


            takeScreenshot("JPetStore_AccountDetails");
            test.log(Status.PASS, "Scrolled to account details section");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to My Account page or scroll");
            test.fail(e);
            return;
        }

        // Verify the account details page
        try {
            WebElement accountDetailsHeader = driver.findElement(By.tagName("h3"));
            Assert.assertEquals(accountDetailsHeader.getText(), "User Information", "Account details page is not displayed as expected.");
            test.log(Status.PASS, "Account details page is displayed correctly");
        } catch (Exception e) {
            test.log(Status.FAIL, "Account details page verification failed");
            test.fail(e);
        }
    }

   

    
}
