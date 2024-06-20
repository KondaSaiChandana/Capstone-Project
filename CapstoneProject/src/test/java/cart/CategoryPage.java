package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
	WebDriver driver;
    
    By bullDog=By.xpath("//a[normalize-space()='K9-BD-01']");
    By Angelfish=By.xpath("//a[normalize-space()='FI-SW-01']");    
    
    public CategoryPage(WebDriver driver) {
  	  this.driver=driver;
    }
    
    public void selectBullDog() {
      WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(10));
  	  driver.findElement(bullDog).click();
    }
   
    public void selectAngelfish() {
      WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
  	  driver.findElement(Angelfish).click();
    }
   
}