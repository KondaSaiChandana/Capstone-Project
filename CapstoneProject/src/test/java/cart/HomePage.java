package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
     WebDriver driver;
     
     By cartIcon=By.name("img_cart");
     
     public HomePage(WebDriver driver) {
    	 this.driver=driver;
     }
     
     public void clickOnCartIcon() {
    	 WebDriverWait wait5=new WebDriverWait(driver, Duration.ofSeconds(10));
    	 driver.findElement(cartIcon).click();
     }  
}