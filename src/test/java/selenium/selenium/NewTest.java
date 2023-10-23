package selenium.selenium;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class NewTest {
	static WebDriver driver;
  @Test
  public void f() {
	  ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test= extent.createTest("my first test","sample description");
		
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe");
//      String chromeDriverPath = "C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe";
//      System.setProperty("webdriver.chrome.driver", chromeDriverPath);

      driver = new ChromeDriver();
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

      driver.get("https://demo.automationtesting.in/Frames.html");
      driver.manage().window().maximize();
      test.log(Status.INFO, "starting test cases");
      test.pass("navigated to automationtesting");
      driver.findElement(By.xpath("//a[normalize-space()='Iframe with in an Iframe']")).click();
      
      WebElement outerFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='MultipleFrames.html']")));
      driver.switchTo().frame(outerFrame);
      
      WebElement innerFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/iframe")));
      driver.switchTo().frame(innerFrame);

      WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
      inputField.sendKeys("kashif");

      driver.switchTo().parentFrame();
      driver.switchTo().defaultContent();

      driver.findElement(By.xpath("//a[normalize-space()='Single Iframe']")).click();

      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      System.out.println(driver.getCurrentUrl());
      test.pass("successfully done");
      driver.quit();
      test.info("completed");
      extent.flush();
	}

}

