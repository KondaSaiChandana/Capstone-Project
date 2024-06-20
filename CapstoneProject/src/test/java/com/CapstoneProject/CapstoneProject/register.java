package com.CapstoneProject.CapstoneProject;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class register extends baseclass {
    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws IOException {
        String excelFilePath = "./excel_sheet/registration_data.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[rowCount - 1][sheet.getRow(0).getPhysicalNumberOfCells()];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }
        workbook.close();
        inputStream.close();
        return data;
    }

    @Test(dataProvider = "registrationData")
    public void testRegistration(String userId, String newPassword, String confirmPassword, String firstName,
                                 String lastName, String email, String phone, String address1, String address2,
                                 String city, String state, String zip, String country, String languagePreference,
                                 String favouriteCategory) throws IOException {
        test = extent.createTest("testRegistration");

        // Navigate to registration page
        try {
            driver.findElement(By.xpath("//*[@id=\"MenuContent\"]/a[3]")).click();
            test.log(Status.INFO, "Navigated to registration page");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to navigate to registration page");
            test.fail(e);
            return;
        }

        // Fill registration form
        try {
            driver.findElement(By.name("username")).sendKeys(userId);
            test.log(Status.INFO, "Entered username");

            driver.findElement(By.name("password")).sendKeys(newPassword);
            test.log(Status.INFO, "Entered password");

            driver.findElement(By.name("repeatedPassword")).sendKeys(confirmPassword);
            test.log(Status.INFO, "Entered confirm password");

            driver.findElement(By.name("firstName")).sendKeys(firstName);
            test.log(Status.INFO, "Entered first name");

            driver.findElement(By.name("lastName")).sendKeys(lastName);
            test.log(Status.INFO, "Entered last name");

            driver.findElement(By.name("email")).sendKeys(email);
            test.log(Status.INFO, "Entered email");

            driver.findElement(By.name("phone")).sendKeys(phone);
            test.log(Status.INFO, "Entered phone");

            driver.findElement(By.name("address1")).sendKeys(address1);
            test.log(Status.INFO, "Entered address1");

            driver.findElement(By.name("address2")).sendKeys(address2);
            test.log(Status.INFO, "Entered address2");

            driver.findElement(By.name("city")).sendKeys(city);
            test.log(Status.INFO, "Entered city");

            driver.findElement(By.name("state")).sendKeys(state);
            test.log(Status.INFO, "Entered state");

            driver.findElement(By.name("zip")).sendKeys(zip);
            test.log(Status.INFO, "Entered zip");

            driver.findElement(By.name("country")).sendKeys(country);
            test.log(Status.INFO, "Entered country");

            WebElement languageSelect = driver.findElement(By.name("languagePreference"));
            languageSelect.sendKeys(languagePreference);
            test.log(Status.INFO, "Selected language preference");

            WebElement categorySelect = driver.findElement(By.name("favouriteCategoryId"));
            categorySelect.sendKeys(favouriteCategory);
            test.log(Status.INFO, "Selected favourite category");

            WebElement myListCheckbox = driver.findElement(By.name("listOption"));
            if (!myListCheckbox.isSelected()) {
                myListCheckbox.click();
            }
            test.log(Status.INFO, "Selected myList option");

            WebElement myBannerCheckbox = driver.findElement(By.name("bannerOption"));
            if (!myBannerCheckbox.isSelected()) {
                myBannerCheckbox.click();
            }
            test.log(Status.INFO, "Selected myBanner option");

            WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"CenterForm\"]/form/div/button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            saveButton.click();
            test.log(Status.INFO, "Clicked on save button");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to fill the registration form");
            test.fail(e);
            return;
        }

        // Verify registration success
        try {
            Thread.sleep(2000); // Wait for the success message to appear
            WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(), 'Your account has been created.')]"));
            Assert.assertTrue(successMessage.isDisplayed(), "Registration failed!");
            test.log(Status.PASS, "Registration successful");
            takeScreenshot(userId);
        } catch (Exception e) {
            test.log(Status.FAIL, "Registration verification failed");
            test.fail(e);
            return;
        }
    }
}
