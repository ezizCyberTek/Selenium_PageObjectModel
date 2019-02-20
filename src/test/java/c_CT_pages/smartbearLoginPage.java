package c_CT_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class smartbearLoginPage {
	
	public smartbearLoginPage(WebDriver driver) {
		//using the driver initialize or locate all elements in the class
		PageFactory.initElements(driver, this);
	}
	
	//using @FindBy specify a way to locate the webElement
	@FindBy(id="ctl00_MainContent_username")
	public WebElement userName;
	
	@FindBy(id="ctl00_MainContent_password")
	public WebElement password;
	
	@FindBy(id="ctl00_MainContent_login_button")
	public WebElement loginButton;
	
	@FindBy(id="ctl00_MainContent_status")
	public WebElement invalidLoginMessage;
	
	public void login(WebDriver driver, String uid, String pwd) {
		userName.sendKeys(uid);
		password.sendKeys(pwd);
		loginButton.click();
		
		WebDriverWait explWait=new WebDriverWait(driver,7);
		explWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_logout"))));
		
		Assert.assertTrue(driver.findElement(By.id("ctl00_logout")).isDisplayed(),"Login is not successful.");
		
	}
}
