package com.CapstoneProject.CapstoneProject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class homepage extends baseclass {

    @Test
    public void navigateJPetStore() throws InterruptedException {
        test = extent.createTest("navigateJPetStore");

        try {
            navigateSection("//div[@id='SidebarContent']//a[normalize-space()='Fish']", 150);
            navigateSection("//*[@id=\"SidebarContent\"]/a[2]", 250);
            navigateSection("//*[@id=\"SidebarContent\"]/a[3]", 250);
            navigateSection("//*[@id=\"SidebarContent\"]/a[4]", 250);
            navigateSection("//*[@id=\"SidebarContent\"]/a[5]", 250);

            navigateMainImage("#MainImageContent > map > area:nth-child(2)", 100);
            navigateMainImage("#MainImageContent > map > area:nth-child(3)", 100);
            navigateMainImage("#MainImageContent > map > area:nth-child(4)", 100);
            navigateMainImage("#MainImageContent > map > area:nth-child(5)", 100);
            navigateMainImage("#MainImageContent > map > area:nth-child(6)", -100);

            navigateQuickLink("#QuickLinks > a:nth-child(1)");
            navigateQuickLink("#QuickLinks > a:nth-child(2)");
            navigateQuickLink("#QuickLinks > a:nth-child(3)");
            navigateQuickLink("#QuickLinks > a:nth-child(4)");
            navigateQuickLink("#QuickLinks > a:nth-child(5)");

            navigateMenuContent("#MenuContent > a:nth-child(1)", 200);
            navigateMenuContent("#MenuContent > a:nth-child(3)", 200);
            navigateMenuContent("#MenuContent > a:nth-child(5)", 200);

            test.log(Status.PASS, "Navigation completed successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Navigation failed");
            test.fail(e);
        }
    }

    private void navigateSection(String locator, int scrollValue) throws InterruptedException, IOException {
        WebElement section = driver.findElement(By.xpath(locator));
        section.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + scrollValue + ")");
        Thread.sleep(2000);
        driver.navigate().back();
        test.log(Status.INFO, "Navigated section");
        takeScreenshot("navigateSection");
    }

    private void navigateMainImage(String locator, int scrollValue) throws InterruptedException, IOException {
        WebElement mainImageArea = driver.findElement(By.cssSelector(locator));
        mainImageArea.sendKeys(Keys.ENTER);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + scrollValue + ")");
        Thread.sleep(2000);
        driver.navigate().back();
        test.log(Status.INFO, "Navigated main image area");
        takeScreenshot("navigateMainImage");
    }

    private void navigateQuickLink(String locator) throws InterruptedException, IOException {
        WebElement quickLink = driver.findElement(By.cssSelector(locator));
        quickLink.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.navigate().back();
        test.log(Status.INFO, "Navigated quick link");
        takeScreenshot("navigateQuickLink");
    }

    private void navigateMenuContent(String locator, int scrollValue) throws InterruptedException, IOException {
        WebElement menuContentLink = driver.findElement(By.cssSelector(locator));
        menuContentLink.sendKeys(Keys.ENTER);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + scrollValue + ")");
        Thread.sleep(2000);
        driver.navigate().back();
        test.log(Status.INFO, "Navigated menu content link");
        takeScreenshot("navigateMenuContent");
    }

}
