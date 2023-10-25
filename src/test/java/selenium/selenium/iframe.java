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

public class iframe {
	
	static WebDriver driver;
	@Test
	public void f(){
ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("iframe.html");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test= extent.createTest("my first test","sample description");
    	
    	System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe");
//        String chromeDriverPath = "C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        test.log(Status.INFO, "starting test cases");
        driver.findElement(By.xpath("//button[normalize-space()='Click for JS Alert']")).click();
        test.pass("click on the js alert");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        org.openqa.selenium.Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();

        test.pass("successfully done");
        driver.quit();
        test.info("completed");
        extent.flush();;
    }
}
