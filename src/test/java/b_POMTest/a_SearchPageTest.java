package b_POMTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import b_POMClasses.a_SearchPageFactory;



public class a_SearchPageTest {
	
	private WebDriver driver;
	private String baseURL;
	a_SearchPageFactory searchPageObj;
	private WebDriverWait explWait;
	
	@BeforeMethod
	public void setUp() {
	    
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\ekurbanov\\work\\chromedriver.exe");
	    driver = new ChromeDriver();
	    baseURL = "https://www.expedia.com/";
	    
	    searchPageObj = new a_SearchPageFactory(driver);
	    explWait=new WebDriverWait(driver,20);
	    
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	@Test
	public void test() throws InterruptedException {
		searchPageObj.clickFlightsTab();
		searchPageObj.selectRoundTrip();
		searchPageObj.setFlyingFrom("New York");
		searchPageObj.setFlyingTo("Chicago");
		searchPageObj.enterDepartingDate("Jul", "3");
		searchPageObj.enterReturningDate("Jul", "5");
		searchPageObj.clickSearchButton();
		explWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='loader loader-primary loader-light loader-animated loading pageInterstitialLoader']")));
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.close();
	}	
	
}
