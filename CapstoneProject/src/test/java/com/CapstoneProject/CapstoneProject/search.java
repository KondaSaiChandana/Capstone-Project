package com.CapstoneProject.CapstoneProject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class search extends baseclass {

    @Test(priority=0)
    public void findsearchbox() throws InterruptedException, IOException {
        try {
            driver.get("https://jpetstore.aspectran.com/");
            WebElement searchbox=driver.findElement(By.xpath("//*[@id=\"SearchContent\"]/form/div/input"));
            takeScreenshot("findsearchbox");
            test.log(Status.PASS, "Search box found and screenshot taken");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case findsearchbox failed");
            test.fail(e);
        }
        Thread.sleep(2000);
    }

    @Test(priority=1)
    public void sendkeys() throws InterruptedException, IOException {
        test=extent.createTest("sendkeys");
        try {
            String item="Dog";
            driver.get("https://jpetstore.aspectran.com/");
            WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"SearchContent\"]/form/div/input"));
            searchbox.sendKeys(item);
            takeScreenshot("sendkeys");
            test.log(Status.PASS, "Sent keys to search box and screenshot taken");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case sendkeys failed");
            test.fail(e);
        }
        Thread.sleep(2000);
    }

    @Test(priority=2)
    public void search() throws InterruptedException, IOException {
        test = extent.createTest("search");
        try {
            driver.get("https://jpetstore.aspectran.com/");
            WebElement searchbox=driver.findElement(By.xpath("//*[@id=\"SearchContent\"]/form/div/input"));
            searchbox.sendKeys("Fish");
            WebElement search=driver.findElement(By.cssSelector("#SearchContent > form > div > div > button"));
            search.click();
            WebElement tableBody=driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody"));
            List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

            boolean hasDataRows=false;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size()>0) {
                    hasDataRows=true;
                    break;
                }
            }
            takeScreenshot("search");
            Assert.assertTrue(hasDataRows, "results not found");
            test.log(Status.PASS, "Search functionality working, screenshot taken");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case search failed");
            test.fail(e);
        }
        Thread.sleep(2000);
    }

    @Test(priority=3)
    public void validsearch() throws IOException {
        test=extent.createTest("validsearch");
        try {
            String item="goldfish";
            WebElement searchbox=driver.findElement(By.xpath("//*[@id=\"SearchContent\"]/form/div/input"));
            searchbox.sendKeys(item);
            searchbox.submit();

            WebElement result=driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[2]"));
            takeScreenshot("validsearch");
            Assert.assertTrue(result.getText().toLowerCase().contains(item), "Search result failed to get valid result");
            test.log(Status.PASS, "Valid search, screenshot taken");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case validsearch failed");
            test.fail(e);
        }
    }

    @Test(priority=4)
    public void emptysearch() throws InterruptedException, IOException {
        test = extent.createTest("emptysearch");
        try {
            WebElement searchbox=driver.findElement(By.xpath("//*[@id=\"SearchContent\"]/form/div/input"));
            searchbox.clear();
            searchbox.submit();
            Thread.sleep(2000);

            WebElement tableBody=driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody"));
            List<WebElement> rows=tableBody.findElements(By.tagName("tr"));

            boolean hasDataRows=false;
            for (WebElement row:rows) {
                List<WebElement> cells=row.findElements(By.tagName("td"));
                if (cells.size()>0) {
                    hasDataRows=true;
                    break;
                }
            }
            takeScreenshot("emptysearch");
            Assert.assertFalse(hasDataRows, "result found");
            test.log(Status.PASS, "Empty search, screenshot taken");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case emptysearch failed");
            test.fail(e);
        }
    }

    @Test(priority=5)
    public void invalidsearch() throws IOException {
        test=extent.createTest("invalidsearch");
        try {
            String item="Invalid search";
            WebElement searchbox=driver.findElement(By.xpath("//*[@id=\"SearchContent\"]/form/div/input"));
            searchbox.sendKeys(item);
            searchbox.submit();

            WebElement tableBody=driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody"));
            List<WebElement> rows=tableBody.findElements(By.tagName("tr"));

            boolean hasDataRows=false;
            for (WebElement row:rows) {
                List<WebElement> cells=row.findElements(By.tagName("td"));
                if (cells.size()>0) {
                    hasDataRows=true;
                    break;
                }
            }
            takeScreenshot("invalidsearch");
            Assert.assertFalse(hasDataRows, "Result Found");
            test.log(Status.PASS, "Invalid search, screenshot taken");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test case invalidsearch failed");
            test.fail(e);
        }
    }
}
