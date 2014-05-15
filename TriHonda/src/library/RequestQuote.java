package library;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RequestQuote{
Sheet s;
WebDriver driver;
private StringBuffer verificationErrors = new StringBuffer();
static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

@Before
	public void setUp()
	{
	driver = new FirefoxDriver();
	//((Rotatable) driver).rotate(ScreenOrientation.LANDSCAPE);
	driver.manage().window().maximize();
	
	}


@Test
	public void searchGoogle() throws Exception {
	
	InputStream fi = this.getClass().getResourceAsStream("info.xls");
	Workbook w = Workbook.getWorkbook(fi);
	s = w.getSheet(0);
	//Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	//String browserName = caps.getBrowserName();
	//String browserVersion = caps.getVersion();
	//System.out.println(browserName+" "+browserVersion);
	//System.out.println("==========");

for(int row=1; row <=s.getRows();row++) {
	
   // String baseurl="http://www.google.com";
   // driver.get(baseurl);

	String urlname = s.getCell(0, row).getContents();
	driver.get(urlname);
	Thread.sleep(1000);
    File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile1, new File("Y://Screenshots/RequestQuote/" + timeStamp + "/" + row  + "-" + "hondacar.png"));
	
	//((Rotatable) driver).rotate(ScreenOrientation.PORTRAIT);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//Thread.sleep(3000);

	
	
	
	   //driver.findElement(By.cssSelector("a.request-button.blue-button > span")).click();
	   
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
	    driver.findElement(By.cssSelector("div.button > button.blue-button")).click();
		Thread.sleep(1000);
	//System.out.println(driver.getCurrentUrl() + " - " + driver.getTitle() + " - landscape");

	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
    File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile2, new File("Y://Screenshots/" +   "/" + row +  "-" + timeStamp + "-" + "success.png"));
	


	//System.out.println(driver.getCurrentUrl() + " - " + driver.getTitle() + " - portrait");

	
   // File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//FileUtils.copyFile(scrFile2, new File("Y://Screenshots/" +  browserName + browserVersion + "/" + row +   "-portrait.png"));
	//((Rotatable) driver).rotate(ScreenOrientation.LANDSCAPE);
	
	}
	}



private boolean isElementPresent(By cssSelector) {
	// TODO Auto-generated method stub
	return false;
}


@After
	public void tearDown() throws Exception {
	 
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}