package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
   WebDriver driver;
   
   By fish=By.linkText("Fish");
   By dogs=By.linkText("Dogs");
   
   public CartPage(WebDriver driver) {
  	 this.driver=driver;
   }
   
   public void clickOnFishCategory(){
	   WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
	   driver.findElement(fish).click();
   }
   
   public void clickOnDogsCategory(){
	   WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(10));
	   driver.findElement(dogs).click();  
   }
 
}