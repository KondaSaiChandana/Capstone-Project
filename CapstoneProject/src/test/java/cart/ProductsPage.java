package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage{
	WebDriver driver;
	  By maleAdultBullDog=By.xpath("//tbody/tr[2]/td[5]/a[1]");
	  By femalePuppyBullDog=By.xpath("//tbody/tr[3]/td[5]/a[1]");
	  By largeAngelfish=By.xpath("//tbody/tr[2]/td[5]/a[1]");
	  By smallAngelfish=By.xpath("//tbody/tr[3]/td[5]/a[1]");
	  
	  public ProductsPage(WebDriver driver) {
		  this.driver=driver;
	  }
	  
	  public void addMaleAdultBullDog() {
		  WebDriverWait wait6=new WebDriverWait(driver, Duration.ofSeconds(10));
		  driver.findElement(maleAdultBullDog).click();
	  }
	  public void addFemalePuppyBullDog() {
		  WebDriverWait wait7=new WebDriverWait(driver, Duration.ofSeconds(10));
		  driver.findElement(femalePuppyBullDog).click();
	  }
	  
	  public void addLargeAngelfish() {
		  WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(10));
		  driver.findElement(largeAngelfish).click();
	  }
	  public void addSmallAngelfish() {
		  WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(10));
		  driver.findElement(smallAngelfish).click();
	  }

}