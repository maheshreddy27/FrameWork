package genric;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseClass implements IAutoConstants {
	
	static {
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	
	public WebDriver driver;
	@BeforeMethod(alwaysRun=true)
	public void openApp(String ip,String browser) {
     driver=Utility.openBrowser(ip, browser);
     driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
     driver.get("https://www.google.com/");
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApp() {
		
	}

}
