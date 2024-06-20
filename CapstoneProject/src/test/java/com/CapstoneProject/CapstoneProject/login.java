package com.CapstoneProject.CapstoneProject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class login extends baseclass {

    @Test
    public void testLogin() throws InterruptedException, IOException {
        test = extent.createTest("testLogin");

        try {
            WebElement signin = driver.findElement(By.cssSelector("a[href='/account/signonForm']"));
            signin.click();
            test.log(Status.INFO, "Clicked on Sign In");

            WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
            username.clear();
            username.sendKeys(props.getProperty("username"));
            test.log(Status.INFO, "Entered username");

            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
            password.clear();
            password.sendKeys(props.getProperty("password"));
            test.log(Status.INFO, "Entered password");

            WebElement login = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
            login.click();
            test.log(Status.INFO, "Clicked on Login");
            
            Thread.sleep(2000);
            takeScreenshot("testLogin");
            test.log(Status.PASS, "Login successful").addScreenCaptureFromPath("screenshots/testLogin.png");

            WebElement signout = driver.findElement(By.xpath("//a[normalize-space()='Sign Out']"));
            signout.click();
            test.log(Status.INFO, "Clicked on Sign Out");

            Thread.sleep(3000);
            test.log(Status.PASS, "Sign Out successful");
        } catch (Exception e) {
            test.log(Status.FAIL, "Login test failed");
            test.fail(e);
        }
    }

    @Test
    public void testInvalidUsernameandpassword() throws InterruptedException, IOException {
        test = extent.createTest("testInvalidUsernameandpassword");

        try {
            WebElement signin = driver.findElement(By.cssSelector("a[href='/account/signonForm']"));
            signin.click();
            test.log(Status.INFO, "Clicked on Sign In");

            WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
            username.clear();
            username.sendKeys(props.getProperty("invalidusername"));
            test.log(Status.INFO, "Entered invalid username");

            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
            password.clear();
            password.sendKeys(props.getProperty("invalidpassword"));
            test.log(Status.INFO, "Entered invalid password");

            WebElement login = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
            login.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500)");

            test.log(Status.INFO, "Clicked on Login");

            
            Thread.sleep(5000);
            takeScreenshot("testInvalidUsernameandpassword");
            test.log(Status.PASS, "Invalid login attempt logged").addScreenCaptureFromPath("screenshots/testInvalidUsernameandpassword.png");
        } catch (Exception e) {
            test.log(Status.FAIL, "Invalid username and password test failed");
            test.fail(e);
        }
    }

    @Test
    public void testEmptyusernameandpassword() throws InterruptedException, IOException {
        test = extent.createTest("testEmptyusernameandpassword");

        try {
            WebElement signin = driver.findElement(By.cssSelector("a[href='/account/signonForm']"));
            signin.click();
            test.log(Status.INFO, "Clicked on Sign In");

            WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
            username.clear();
            username.sendKeys(props.getProperty("emptyusername"));
            test.log(Status.INFO, "Entered empty username");

            WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
            password.clear();
            password.sendKeys(props.getProperty("emptypassword"));
            test.log(Status.INFO, "Entered empty password");

            WebElement login = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
            login.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500)");

            test.log(Status.INFO, "Clicked on Login");
            
            Thread.sleep(5000);
            takeScreenshot("testEmptyusernameandpassword");
            test.log(Status.PASS, "Empty login attempt logged").addScreenCaptureFromPath("screenshots/testEmptyusernameandpassword.png");

            WebElement errormessage = driver.findElement(By.xpath("//div[@class='panel failed']"));
            Assert.assertTrue(errormessage.isDisplayed());
            test.log(Status.PASS, "Error message displayed for empty username and password");
        } catch (Exception e) {
            test.log(Status.FAIL, "Empty username and password test failed");
            test.fail(e);
        }
    }
}
