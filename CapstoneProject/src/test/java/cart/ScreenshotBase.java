package cart;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotBase {
	
	WebDriver driver;
	
	public ScreenshotBase(WebDriver driver) {
		this.driver=driver;
	}
	public void takeScreenshot(String baseFileName){
		File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName=baseFileName+"-"+ timestamp + ".png";
		File destinationFile=new File("./screenshots/"+fileName);
		
		try {
			FileHandler.createDir(new File("screenshots"));
			FileHandler.copy(screenshot,  destinationFile);
			System.out.println("Screenshot saved as :"+destinationFile.getAbsolutePath());
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
		  
}