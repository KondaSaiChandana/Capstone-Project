package cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowserBase {
	protected static WebDriver driver;
	
	public SelectBrowserBase(WebDriver driver) {
		this.driver=driver;
	}
	public static WebDriver selectBrowser(String browserName) {
		 
		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://jpetstore.aspectran.com/");
			
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("https://jpetstore.aspectran.com/");
			
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.get("https://jpetstore.aspectran.com/");
			
		
	    }else {
	    	System.out.println("Sorry! Browser not found.");
	    }
		return driver;
	}

}