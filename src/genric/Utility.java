package genric;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Utility {
	
	public String getExcelData(String path,String sheet, int row, int cell) {
		String v="";
		try {
			Workbook w=WorkbookFactory.create(new FileInputStream(path));
			v=w.getSheet(sheet).getRow(row).getCell(cell).toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return v;
	}
	
	public void writeExcelData(String path,int passCount, int failCount) {
		
		try {
			
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			wb.getSheet("Saheet1").getRow(1).getCell(0).setCellValue(passCount);
			wb.getSheet("Saheet1").getRow(1).getCell(1).setCellValue(failCount);
			wb.write(new FileOutputStream(path));
			wb.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	
	public String screenShot(WebDriver driver, String folder) {
		Date d=new Date();
		String dateTime = d.toString().replaceAll(":", "_");
		String path=folder+"/"+dateTime+".png";
		try {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File scr = ts.getScreenshotAs(OutputType.FILE);
			File des = new File(path);
			
			FileUtils.copyDirectory(scr, des);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return path;
		
	}
	
	public static WebDriver openBrowser(String ip, String browser) {
		WebDriver driver;
		
		if(ip.equals("localhost")) {
			if(browser.equals("chrome")) {
				driver=new ChromeDriver();
			}
			else {
				driver=new FirefoxDriver();
			}
		}
		
		else {
			try{
				URL url=new URL("http://"+ip+":4444/wd/hub");
				DesiredCapabilities dc=new DesiredCapabilities();
				dc.setBrowserName(browser);
				driver=new RemoteWebDriver(url,dc);
			}
			catch (Exception e) {
				e.printStackTrace();
				driver=new ChromeDriver();
           }
		}
		return driver;
	}

	
}
