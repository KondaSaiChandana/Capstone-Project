package com.CapstoneProject.CapstoneProject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class payment extends baseclass {
    @Test
    public void testOrderProcess() throws IOException, InterruptedException {

        test.log(Status.INFO, "testOrderProcess");

        try {
            // Sign in
            test.log(Status.INFO, "Navigating to Sign In page");
            driver.findElement(By.linkText("Sign In")).click();
            WebElement username = driver.findElement(By.name("username"));
            username.clear();
            username.sendKeys(props.getProperty("username"));
            WebElement password = driver.findElement(By.name("password"));
            password.clear();
            password.sendKeys(props.getProperty("password"));
            driver.findElement(By.cssSelector("#Signon > form > div > div > button")).click();
            Thread.sleep(2000);
            test.log(Status.INFO, "Signed in successfully");

            // Select item
            test.log(Status.INFO, "Selecting item from catalog");
            driver.findElement(By.cssSelector("#MyList > div > ul > li:nth-child(1) > a")).click();            Thread.sleep(2000);
            driver.findElement(By.cssSelector("#Catalog > table > tbody > tr:nth-child(2) > td:nth-child(5) > a")).click();            Thread.sleep(2000);


            // Add to cart and proceed to checkout
            test.log(Status.INFO, "Adding item to cart and proceeding to checkout");
            driver.findElement(By.cssSelector("#Cart > a")).click();
            
            Thread.sleep(2000); // Waiting for the page to load

            // Read billing details from Excel
            test.log(Status.INFO, "Reading billing details from Excel");
           FileInputStream file = new FileInputStream(new File("./excel_sheet/billingDetails.xlsx"));
          //  InputStream file = payment.class.getResourceAsStream("/CapstoneProject/src/main/java/resource/billingDetails.xlsx");
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);

            String cardType = row.getCell(0).getStringCellValue();
            double cardNumber = row.getCell(1).getNumericCellValue();
            Date expiryDate = row.getCell(2).getDateCellValue();
            String firstName = row.getCell(3).getStringCellValue();
            String lastName = row.getCell(4).getStringCellValue();
            String address1 = row.getCell(5).getStringCellValue();
            String address2 = row.getCell(6).getStringCellValue();
            String city = row.getCell(7).getStringCellValue();
            String state = row.getCell(8).getStringCellValue();
            double zip = row.getCell(9).getNumericCellValue();
            String country = row.getCell(10).getStringCellValue();

            file.close();

            String formattedCardNumber = String.valueOf((long) cardNumber);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            String formattedExpiryDate = dateFormat.format(expiryDate);
            String formattedZip = String.valueOf((int) zip);

            // Enter payment details
            test.log(Status.INFO, "Entering payment details");
            Select cardTypeSelect = new Select(driver.findElement(By.cssSelector("#CenterForm > form > table:nth-child(4) > tbody > tr:nth-child(1) > td:nth-child(2) > select")));
            cardTypeSelect.selectByVisibleText(cardType);
            WebElement creditCardField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[1]/tbody/tr[2]/td[2]/input"));
            creditCardField.clear();
            creditCardField.sendKeys(formattedCardNumber);

            WebElement expiryDateField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[1]/tbody/tr[3]/td[2]/input"));
            expiryDateField.clear();
            expiryDateField.sendKeys(formattedExpiryDate);

            WebElement firstNameField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[1]/td[2]/input"));
            firstNameField.clear();
            firstNameField.sendKeys(firstName);

            WebElement lastNameField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[2]/td[2]/input"));
            lastNameField.clear();
            lastNameField.sendKeys(lastName);

            WebElement address1Field = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[3]/td[2]/input"));
            address1Field.clear();
            address1Field.sendKeys(address1);

            WebElement address2Field = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[4]/td[2]/input"));
            address2Field.clear();
            address2Field.sendKeys(address2);

            WebElement cityField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[5]/td[2]/input"));
            cityField.clear();
            cityField.sendKeys(city);

            WebElement stateField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[6]/td[2]/input"));
            stateField.clear();
            stateField.sendKeys(state);

            WebElement zipField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[7]/td[2]/input"));
            zipField.clear();
            zipField.sendKeys(formattedZip);

            WebElement countryField = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[8]/td[2]/input"));
            countryField.clear();
            countryField.sendKeys(country);

            // Scroll down and click continue
            WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/div/button[1]"));
            ((RemoteWebDriver) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
            continueButton.click();
            Thread.sleep(2000);

            // Confirm order
            WebElement confirmButton = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/div/button[1]"));
            ((RemoteWebDriver) driver).executeScript("arguments[0].scrollIntoView(true);", confirmButton);
            confirmButton.click();
            Thread.sleep(2000);


            // Verify order confirmation
            String confirmationMessage = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue(confirmationMessage.contains("Thank you, your order has been submitted."));
            test.log(Status.PASS, "Order process test passed");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            takeScreenshot("testOrderProcess_Failure");
            throw e;
        }
    }
}
