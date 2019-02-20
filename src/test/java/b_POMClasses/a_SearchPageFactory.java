package b_POMClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class a_SearchPageFactory {
	WebDriver driver;
	JavascriptExecutor js;
	
	public a_SearchPageFactory(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor)this.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="tab-flight-tab-hp")
	WebElement flightsTab;
	
	@FindBy(id="flight-type-roundtrip-label-hp-flight")
	WebElement roundTrip;
	
	@FindBy(id="flight-type-one-way-label-hp-flight")
	WebElement oneWay;
	
	@FindBy(id="flight-type-multi-dest-label-hp-flight")
	WebElement multiCity;
	
	@FindBy(id="flight-origin-hp-flight")
	WebElement flyingFrom;
	
	@FindBy(id="flight-destination-hp-flight")
	WebElement flyingTo;
	
	@FindBy(id="flight-departing-hp-flight")
	WebElement departingDate;
	
	@FindBy(id="flight-returning-hp-flight")
	WebElement returningDate;
	
	@FindBy(xpath="//div[@id='traveler-selector-hp-flight']//button[@class='trigger-utility menu-trigger btn-utility btn-secondary dropdown-toggle theme-standard pin-left menu-arrow gcw-component gcw-traveler-amount-select gcw-component-initialized']")
	WebElement travelersButton;
	
	@FindBy(xpath="//form[@id='gcw-flights-form-hp-flight']//button[@class='btn-primary btn-action gcw-submit']")
	WebElement searchButton;
	
	public void clickFlightsTab() {
		flightsTab.click();
	}
	
	public void selectOneWay() {
		oneWay.click();
	}
	
	public void selectMultiCity() {
		multiCity.click();
	}
	
	public void selectRoundTrip() {
		roundTrip.click();
	}
	
	public void setFlyingFrom(String flyingFromCity) throws InterruptedException {
		flyingFrom.sendKeys(flyingFromCity);
		Thread.sleep(1000);
		
		/*String text;
		 * do { flyingFrom.sendKeys(Keys.DOWN); text = (String) js.
		 * executeScript("return document.getElementById(\"package-origin-hp-package\").value;"
		 * ); } while(!text.toLowerCase().contains(flyingFromCity.toLowerCase()));
		 */
		flyingFrom.sendKeys(Keys.DOWN);
		flyingFrom.sendKeys(Keys.ENTER);
		
	}
	
	
	public void setFlyingTo(String flyingToCity) throws InterruptedException {
		flyingTo.sendKeys(flyingToCity);
		Thread.sleep(1000);
		
		/*
		 * JavascriptExecutor js = (JavascriptExecutor)driver; String script =
		 * "return document.getElementById(\"package-destination-hp-package\").value;";
		 * String text;
		 * 
		 * do { flyingTo.sendKeys(Keys.DOWN); text = (String) js.executeScript(script);
		 * } while(!text.toLowerCase().contains(flyingToCity.toLowerCase()));
		 */
		flyingTo.sendKeys(Keys.DOWN);
		flyingTo.sendKeys(Keys.ENTER);
	}
	
	
	public void enterDepartingDate(String departureMonth, String departureDate) throws InterruptedException {
		departingDate.click();
		Thread.sleep(1000);
		selectMonthAndDate(departureMonth, departureDate);
	}
	
	public void enterReturningDate(String returnMonth, String returnDate) throws InterruptedException {
		returningDate.click();
		Thread.sleep(1000);
		selectMonthAndDate(returnMonth, returnDate);
	}
	
	
	public void selectMonthAndDate(String paramMonth, String paramDate) {
		
		//Month
		int monthIndex;
		
		while(true) {
			String month1 = driver.findElement(By.xpath("//div[@class='datepicker-dropdown']/div/div[2]//caption")).getText();
			String month2 = driver.findElement(By.xpath("//div[@class='datepicker-dropdown']/div/div[3]//caption")).getText();
			
			if(month1.contains(paramMonth)) {
				monthIndex=2;
				break;
			} else if(month2.contains(paramMonth)) {
				monthIndex=3;
				break;
			} else {
				driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']")).click();		
			}
			
		}
		
		//Date  
		int dateCount;
		if(monthIndex==2) {
			dateCount = driver.findElements(By.xpath("//div[@class='datepicker-dropdown']/div/div[2]//button[@class='datepicker-cal-date']")).size();
			for(int i=0; i<dateCount; i++){
				String date = driver.findElements(By.xpath("//div[@class='datepicker-dropdown']/div/div[2]//button[@class='datepicker-cal-date']")).get(i).getText();
				if(date.equalsIgnoreCase(paramDate)) {
					driver.findElements(By.xpath("//div[@class='datepicker-dropdown']/div/div[2]//button[@class='datepicker-cal-date']")).get(i).click();
					 break;
				}
			}
			
		} else {
			
			dateCount = driver.findElements(By.xpath("//div[@class='datepicker-dropdown']/div/div[3]//button[@class='datepicker-cal-date']")).size();
			for(int i=0; i<dateCount; i++){
				String date = driver.findElements(By.xpath("//div[@class='datepicker-dropdown']/div/div[3]//button[@class='datepicker-cal-date']")).get(i).getText();
				if(date.contains(paramDate)) {
					driver.findElements(By.xpath("//div[@class='datepicker-dropdown']/div/div[3]//button[@class='datepicker-cal-date']")).get(i).click();
					 break;
				}
			}
		}	
	}
	
	
	public void clickSearchButton() {
		searchButton.click();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
