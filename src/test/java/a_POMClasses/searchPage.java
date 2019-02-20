package a_POMClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class searchPage {
	
public static WebElement element = null;
	
	/*
	 * Returns the flight origin text box element
	 * @param driver
	 * @return
	 */
	public static WebElement originTextBox(WebDriver driver) {
		element = driver.findElement(By.id("package-origin-hp-package"));
		return element;
	}
	
	public static void fillOriginTextBox(WebDriver driver, String origin) throws InterruptedException {
		element = originTextBox(driver);
		element.sendKeys(origin);
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return document.getElementById(\"package-origin-hp-package\").value;";
		String text;
		
		do {
			originTextBox(driver).sendKeys(Keys.DOWN);
			text = (String) js.executeScript(script);
		} while(!text.toLowerCase().contains(origin.toLowerCase()));
		
		originTextBox(driver).sendKeys(Keys.ENTER);
		
	}
	
	/*
	 * Returns the flight destination text box element
	 * @param driver
	 * @return
	 */
	public static WebElement destinationTextBox(WebDriver driver) {
		element = driver.findElement(By.id("package-destination-hp-package"));
		return element;
	}
	
	public static void fillDestinationTextBox(WebDriver driver, String destination) throws InterruptedException {
		element = destinationTextBox(driver);
		element.sendKeys(destination);
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String script = "return document.getElementById(\"package-destination-hp-package\").value;";
		String text = (String) js.executeScript(script);
		
		do {
			destinationTextBox(driver).sendKeys(Keys.DOWN);
			text = (String) js.executeScript(script);
		} while(!text.toLowerCase().contains(destination.toLowerCase()));
		
		destinationTextBox(driver).sendKeys(Keys.ENTER);
	}
	 
	/*
	 * Returns the departure date text box element
	 * @param driver
	 * @return
	 */
	public static WebElement departureDateTextBox(WebDriver driver) {
		element = driver.findElement(By.id("package-departing-hp-package"));
		return element;
	}
	
	public static void fillDepartureDateTextBox(WebDriver driver, String departureMonth, String departureDate) {
		element = departureDateTextBox(driver);
		element.click();
		selectMonthAndDate(driver, departureMonth, departureDate);
	}
	
	public static void selectMonthAndDate(WebDriver driver, String paramMonth, String paramDate) {
		
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
	
	 /*
	 * Returns the return date text box element
	 * @param driver
	 * @return
	 */
	public static WebElement returnDateTextBox(WebDriver driver) {
		element = driver.findElement(By.id("package-returning-hp-package"));
		return element;
	}
	
	public static void fillReturnDateTextBox(WebDriver driver, String returnMonth, String returnDate) throws InterruptedException {
		element = returnDateTextBox(driver);
		element.click();
		Thread.sleep(2000);
		selectMonthAndDate(driver, returnMonth, returnDate);
	}
	
	/*
	 * Returns the search button box element
	 * @param driver
	 * @return
	 */
	public static WebElement searchButton(WebDriver driver) {
		element = driver.findElement(By.id("search-button-hp-package"));
		return element;
	}
	
	/*
	 * Click on Search button
	 * @param driver
	 */
	public static void clickOnSearchButton(WebDriver driver) {
		element = searchButton(driver);
		element.click();
	}
	
	/*
	 * Navigate to flights tab
	 * @param driver
	 */
	public static void navigateToFlightsTab(WebDriver driver) {
		//driver.findElement(By.id("header-history")).click();
		element = driver.findElement(By.id("tab-flight-tab"));
		element.click();
	}
	
}
