package a_POMTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import a_POMClasses.searchPage;

public class runSearchPage {
	
	private WebDriver driver;
	private WebDriverWait explWait;

	
	@BeforeMethod
	public void setUp() throws Exception {
	    
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\ekurbanov\\work\\chromedriver.exe");
	    driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://www.expedia.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		explWait=new WebDriverWait(driver,20);
	    
	}
	
	@Test
	public void test() throws InterruptedException {
		searchPage.fillOriginTextBox(driver,"New York");
		searchPage.fillDestinationTextBox(driver,"Chicago");
		searchPage.fillDepartureDateTextBox(driver, "Jun", "2");
		searchPage.fillReturnDateTextBox(driver, "Jun", "7");
		searchPage.clickOnSearchButton(driver);
		explWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='loader loader-primary loader-light loader-animated loading pageInterstitialLoader']")));
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.close();
	}	
	
}
