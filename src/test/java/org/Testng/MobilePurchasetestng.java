package org.Testng;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePurchasetestng {
	
	@DataProvider(name="Mobile")
	public Object[][] mobileName(){
		return new Object[][]{{"samsung"}};
	}
	
	static long start;
	public static WebDriver driver;
	
	
	@Parameters("URL")
	@BeforeClass(groups="default")
	public static void BrowserLaunch(@Optional("Https://www.flipkart.com/")String url) {
		
		WebDriverManager.chromedriver().setup(); 
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}


	
@BeforeMethod(groups="default")
public void startingTime() {
	System.out.println("Before");
	start = System.currentTimeMillis();
	
}

@AfterMethod(groups="default")
public void endTime() {
	long end = System.currentTimeMillis();
	System.out.println("After time taken is :" +(end - start));
}

@Test(priority=1,groups="smoke")
public void login() throws InterruptedException{
	Thread.sleep(3000);
	System.out.println("login");
	WebElement Closeicon =  driver.findElement(By.xpath("//button[text()='✕']"));
	Closeicon.click();
}
	@Test(priority=2,groups="smoke",dataProvider="Mobile")
	public void Search(String name){
		WebElement Searchbar =  driver.findElement(By.name("q"));
		//hardcoded
		//Searchbar.sendKeys("samsung");
		//softcoded
		Searchbar.sendKeys(name,Keys.ENTER);
		
	}
	@Test(priority=3,groups="smoke")
	public void closeicon(){
		WebElement Searchicon =  driver.findElement(By.xpath("//button[@type='submit']"));
		Searchicon.click();
  
}
		
	@Test(priority=4,invocationCount=3 )
	public void GetMobileName() throws InterruptedException{
	
	Actions a = new Actions(driver);

		Thread.sleep(3000);
		
		WebElement newTab =  driver.findElement(By.xpath("//div[text()='SAMSUNG Galaxy F22 (Denim Blue, 128 GB)'][1]"));
		System.out.println(newTab.getText());
		a.moveToElement(newTab).build().perform();
		newTab.click();
		Thread.sleep(3000);
		
			String parentURL = driver.getWindowHandle();
			
			Set<String>allURL = driver.getWindowHandles();
			
			for(String url : allURL){
				
				if(!url.equals(parentURL)){
					driver.switchTo().window(parentURL);
					
				}
				
			}
		
			
	}
	
	@Test(priority=5)
	public void ScreenShot() throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(".//target//Report.png");
		FileUtils.copyFile(Source, destination);
			
	}

	//enabled = false for to ignore any method
	//invocation count to run any method for multiple times
	//exclude any group in testing xml to disable more methods
	@Test(priority=6,enabled=false)
	public void Assert(){
	//hardassert
	//Assert.assertEquals("Keerthana","Venkatesh");
	//softassert
	
	SoftAssert s = new SoftAssert();
	s.assertEquals("Keerthana","Venkatesh");
	}


}




