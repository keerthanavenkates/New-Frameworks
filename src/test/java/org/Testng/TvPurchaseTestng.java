package org.Testng;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.edge.EdgeDriver;

public class TvPurchaseTestng {
	
	static long start;
	public static WebDriver driver;
	
	@Parameters("browser")
	@BeforeClass
	public static void BrowserLaunch(String browser) {
		
		if(browser.equals("chrome")){
		
		WebDriverManager.chromedriver().setup(); 
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if (browser.equals("edge")){ 
			
			WebDriverManager.edgedriver().setup(); 
			driver = new EdgeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
		
		
	}

@BeforeMethod
public void startingTime() {
	System.out.println("Before");
	start = System.currentTimeMillis();
	
}

@AfterMethod
public void endTime() {
	long end = System.currentTimeMillis();
	System.out.println("After time taken is :" +(end - start));
}

@Test(priority=1)
public void login() throws InterruptedException{
	Thread.sleep(3000);
	System.out.println("login");
	WebElement Closeicon =  driver.findElement(By.xpath("//button[text()='✕']"));
	Closeicon.click();
}
	@Test(priority=2)
	public void Search(){
		WebElement Searchbar =  driver.findElement(By.name("q"));
		Searchbar.sendKeys("samsungTV");
	}
	@Test(priority=3)
	public void closeicon(){
		WebElement Searchicon =  driver.findElement(By.xpath("//button[@type='submit']"));
		Searchicon.click();
  Assert.assertEquals("Keerthana", "Venkatesh");
}
		
	@Test(priority=4)
	public void GetMobileName() throws InterruptedException{
	
	Actions a = new Actions(driver);

		Thread.sleep(3000);
		
		WebElement newTab =  driver.findElement(By.xpath("//div[text()='SAMSUNG The Frame 2021 Series 163 cm (65 inch) QLED Ultra HD (4K) Smart Tizen TV'][1]28 GB)'][1]"));
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
		File destination = new File(".//target//Report2.png");
		FileUtils.copyFile(Source, destination);
		
		
	}

	
	//enabled = false for to ignore any method
	//invocation count to run any method for multiple times
	@Test(priority=6,enabled=false)
	public void Assert(){
	String name = "Keerthana";
	Assert.assertEquals(name,"Venkatesh");
	}





}




