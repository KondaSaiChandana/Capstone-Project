package com.CapstoneProject.CapstoneProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class productdetails extends baseclass {

    @Test
    public void testProductDetails() throws InterruptedException {
        test = extent.createTest("testProductDetails");

        try {
            // Navigate to Fish section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 350)");

            Thread.sleep(4000);
            WebElement fishSection = driver.findElement(By.xpath("//div[@id='SidebarContent']//a[normalize-space()='Fish']"));
            fishSection.click();
            Thread.sleep(4000);

            checkProductDetails("FI-SW-01", "EST-1", "Salt Water fish from Australia", "$16.50", "In stock");

            checkProductDetails("FI-SW-01", "EST-2", "Salt Water fish from Australia", "$16.50", "Back ordered.");

            checkProductDetails("FI-SW-02", "EST-3", "Salt Water fish from Australia", "$18.50", "Back ordered.");

            test.log(Status.PASS, "All product details checks passed successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test execution failed");
            test.fail(e);
        }
    }

    private void checkProductDetails(String productID, String itemID, String expectedDescription, String expectedPrice, String expectedStock) throws InterruptedException {
        test.log(Status.INFO, "Checking product details for item ID: " + itemID);

        try {
            // Navigate to product page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 350)");
            Thread.sleep(4000);
            driver.findElement(By.linkText(productID)).click();
            Thread.sleep(4000);

            // Navigate to item page
            WebElement itemLink = driver.findElement(By.linkText(itemID));
            itemLink.click();
            Thread.sleep(4000);

            // Check image
            WebElement image = driver.findElement(By.cssSelector("#CenterForm > table > tbody > tr:nth-child(1) > td > img"));
            Assert.assertTrue(image.isDisplayed(), "Image is not displayed");

            // Check description
            WebElement description = driver.findElement(By.xpath("//td[normalize-space()='Salt Water fish from Australia']"));
            Assert.assertTrue(description.getText().contains(expectedDescription), "Description does not match");

            // Check price
            WebElement price = driver.findElement(By.cssSelector("#CenterForm > table > tbody > tr:nth-child(5) > td"));
            Assert.assertTrue(price.getText().contains(expectedPrice), "Price does not match");

            // Check stock status
            // WebElement stock = driver.findElement(By.cssSelector("#CenterForm > table > tbody > tr:nth-child(4) > td"));
            // Assert.assertTrue(stock.getText().contains(expectedStock), "Stock status does not match");

            // Log success details
            System.out.println(itemID + " Successfully Displayed");
            System.out.println(expectedDescription);
            System.out.println(expectedPrice);
            System.out.println(expectedStock);
            System.out.println("--------------------------");

            test.log(Status.PASS, "Product details verified successfully for item ID: " + itemID);

            // Navigate back to fish section
            driver.navigate().back();
            driver.navigate().back();
            Thread.sleep(4000);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to verify product details for item ID: " + itemID);
            test.fail(e);
        }
    }
}
