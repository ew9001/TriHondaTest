package library;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RequestQuoteFirefox{
Sheet s;
WebDriver driver;

public static final String USERNAME = "earlwillis1";
public static final String AUTOMATE_KEY = "XsPyFTirN4mH8aCLMB9A";
public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
public String url_failed="";
public int  count =0;
private StringBuffer verificationErrors = new StringBuffer();
static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
String local=(new java.io.File("").getAbsolutePath());
String data="" + local + "/" + "info.xls";



@Test
	public void searchGoogle() throws Exception {
	DesiredCapabilities caps = DesiredCapabilities.firefox();
	  caps.setCapability("browser", "FireFox");
	  caps.setCapability("browser_version", "22.0");
	  caps.setCapability("os", "Windows");
	  caps.setCapability("os_version", "7");
	  caps.setCapability("browserstack.debug", "true");
	    
	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    driver.get("http://google.com");



	
	    
	InputStream fi=null;
	fi= new FileInputStream(data);
	Workbook w = Workbook.getWorkbook(fi);
	s = w.getSheet(0);
	//Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	//String browserName = caps.getBrowserName();
	//String browserVersion = caps.getVersion();
	//System.out.println(browserName+" "+browserVersion);
	//System.out.println("==========");
    

	
	//((Rotatable) driver).rotate(ScreenOrientation.PORTRAIT);
         
	for(int row=1; row <=s.getRows();row++) {
		try {
			
				
				   // String baseurl="http://www.google.com";
				   // driver.get(baseurl);

					String urlname = s.getCell(0, row).getContents();
					driver.get(urlname);
					Thread.sleep(500);
					WebDriver augmentedDriver = new Augmenter().augment(driver);
					url_failed=urlname;
					count++;
					
					
			driver.findElements(By.cssSelector("div.no-offer-callout-details > a.request-button.blue-button > span"));
			driver.findElement(By.cssSelector("div.no-offer-callout-details > a.request-button.blue-button > span")).click();
		    driver.findElement(By.id("fname")).clear();
		    driver.findElement(By.id("fname")).sendKeys("TestFirst");
		    driver.findElement(By.id("lname")).clear();
		    driver.findElement(By.id("lname")).sendKeys("TestLast");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("donotreply@pkt.com");
		    driver.findElement(By.id("zipcode")).clear();
		    driver.findElement(By.id("zipcode")).sendKeys("11413");
		    driver.findElement(By.id("phone")).clear();
		    driver.findElement(By.id("phone")).sendKeys("2122222222");
		    Thread.sleep(1000);
		    driver.findElement(By.cssSelector("div.button > button.blue-button")).click();
		    System.out.println("About to Save screenshot on URL to find the button" +url_failed);
		    File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("RequestQuoteFirefox" +   "/" + row +  "-" + timeStamp + "-" + "success.png"));
		    
	       
			
	    } catch (Exception ex){
	    	
	    
	    	
	    	WebDriver augmentedDriver = new Augmenter().augment(driver);
	    	System.out.println("Faield to find the button " +count);
	    	File screenshotfailed = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	    	FileUtils.copyFile(screenshotfailed, new File("RequestQuoteFirefoxFailed" +   "/" + count + timeStamp + "-" + "failed.png"));
	        //e.printStackTrace();
	    	System.out.println("Faield to find the button " +url_failed);
	    	continue;
	    }  


	}
	
}
	

private boolean isElementPresent(By cssSelector) {
	// TODO Auto-generated method stub
	return false;
}


@After
	public void tearDown() throws Exception {
	   
	    driver.quit();
	    
	  }


}