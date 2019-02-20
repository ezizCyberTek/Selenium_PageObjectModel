package c_CT_POMDesign;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import c_CT_pages.smartbearLoginPage;
import c_CT_pages.smartbearWebOrdersPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class smartbearWebOrdersTests {
	
	WebDriver driver;
	smartbearLoginPage loginPage;
	smartbearWebOrdersPage webOrdersPage;
	String userID = "Tester";
	String password = "test";

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2ftestcomplete12%2fweborders%2fDefault.aspx");
	}
	
	@BeforeMethod
	public void setUpApplication() {
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2ftestcomplete12%2fweborders%2fDefault.aspx");
		
		loginPage = new smartbearLoginPage(driver);
		webOrdersPage = new smartbearWebOrdersPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		webOrdersPage.logout();
		driver.close();
	}
	
	@Test(priority=1, description="Verify labels and tab links are displayed")
	public void verifyLabels() {
		
		Assert.assertEquals(driver.getTitle(),"Web Orders Login","Login Page is not displayed. Application is down.");
		
		loginPage.login(driver, userID, password);
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(webOrdersPage.webOrdersLabel.isDisplayed(),"'Web Orders' is not displayed");
		soft.assertTrue(webOrdersPage.listOfAllOrdersLabel.isDisplayed(),"'List of All Orders' is not displayed");
		soft.assertTrue(webOrdersPage.greetingsText.getText().contains("Welcome, " + userID + "!"),"Greeting is not displayed");
		soft.assertTrue(webOrdersPage.viewAllOrdersTab.isDisplayed(),"Unable to find 'View all orders' tab");
		soft.assertTrue(webOrdersPage.viewAllProductsTab.isDisplayed(),"Unable to find 'View all products' tab");
		soft.assertTrue(webOrdersPage.orderTab.isDisplayed(),"Unable to find 'Order' tab"); soft.assertAll();
		 
	}
	
}
