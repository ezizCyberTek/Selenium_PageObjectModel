package c_CT_POMDesign;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import c_CT_pages.smartbearLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class smartbearLoginTests {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2ftestcomplete12%2fweborders%2fDefault.aspx");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	
	@Ignore
	@Test
	public void positiveLoginTest() { // Test without using PageObjectModel
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}
	
	
	@Test(priority=1, description="Verify Login positive scenario")
	public void positiveLoginTestUsingPOM() {	// Test using PageObjectModel
		//create object from 'webOrdersLoginPage' class
		smartbearLoginPage loginPage = new smartbearLoginPage(driver);
		loginPage.userName.sendKeys("Tester");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
	}
	
	@Test(priority=2, description="Verify Login negative scenario")
	public void negativeLoginTestUsingPOM() throws InterruptedException {
		//create object from 'webOrdersLoginPage' class
		smartbearLoginPage loginPage = new smartbearLoginPage(driver);
		loginPage.userName.sendKeys("Test");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		
		//waiting explicitly
		WebDriverWait explWait=new WebDriverWait(driver,20);
		explWait.until(ExpectedConditions.visibilityOf(loginPage.invalidLoginMessage));
		
		//soft assert
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(loginPage.invalidLoginMessage.isDisplayed());
		soft.assertAll();
	}
	
	
	
}
