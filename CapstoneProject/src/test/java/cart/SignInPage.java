package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	    WebDriver driver;
	    
		By signin=By.xpath("//a[normalize-space()='Sign In']");
		By username=By.xpath("//input[@name='username']");
		By pwd=By.xpath("//input[@name='password']");
		By login=By.xpath("//button[normalize-space()='Login']");
		
		public SignInPage(WebDriver driver) {
			this.driver=driver;
		}
		
		public void clickOnSignIn() {
			WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.findElement(signin).click();
		}
		
		public void enterUsername() {
			driver.findElement(username).clear();
			driver.findElement(username).sendKeys("Jpetstore");	
		}
		
		public void enterPassword() {
			driver.findElement(pwd).clear();
			driver.findElement(pwd).sendKeys("jpet@123");	
		}
		
		public void clickLogin() {
			driver.findElement(login).click();
				
		}
		
}