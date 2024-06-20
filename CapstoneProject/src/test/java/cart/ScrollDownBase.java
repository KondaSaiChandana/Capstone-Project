package cart;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollDownBase {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    
    public ScrollDownBase(WebDriver driver) {
    	this.driver=driver;
    	this.jsExecutor=(JavascriptExecutor) driver;
    }
    public void scrollDown() {
    	jsExecutor.executeScript("window.scrollBy(0, 300);");
    }
}