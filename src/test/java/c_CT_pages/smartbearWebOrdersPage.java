package c_CT_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class smartbearWebOrdersPage {
	
	public smartbearWebOrdersPage(WebDriver driver) {
		//using the driver initialize or locate all elements in the class
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[text()='Web Orders']")
	public WebElement webOrdersLabel;
	
	@FindBy(xpath="//h2[contains(text(),'List of All Orders')]")
	public WebElement listOfAllOrdersLabel;
	
	@FindBy(className="login_info")
	public WebElement greetingsText;
	
	@FindBy(linkText="View all orders")
	public WebElement viewAllOrdersTab;
	
	@FindBy(linkText="View all products")
	public WebElement viewAllProductsTab;
	
	@FindBy(linkText="Order")
	public WebElement orderTab;
	
	@FindBy(id="ctl00_logout")
	public WebElement logoutLink;
	
	public void logout() {
		logoutLink.click();
	}
	
}
