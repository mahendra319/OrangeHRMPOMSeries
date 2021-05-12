package com.ohrm.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ohrm.base.BasePage;




public class ElementUtil {

	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 * this method is used to create WebElement by providing locator
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(BasePage.flashElement)) {
			jsUtil.flash(element);
		}
		
		return element;
	}
	
	/**
	 * this method is used to get current page url
	 * @return String
	 */
	public String doGetPageUrl() {
		return driver.getCurrentUrl();
	}
	
	/**
	 * this method is used to get page title
	 * @return String
	 */
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	/**
	 * this method is used to enter value into WebElement by providing By locator 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	/**
	 * this method is used to click on WebElement by providing By locator
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
		
	}
	
	/**
	 * this method is used to enter the values using Actions class methods
	 * @param locator
	 * @param value
	 */
	public void doActionsSendkey(By locator, String value) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getElement(locator), value).build().perform();
	}
	
	/**
	 * this method is used to click on WebElement using Actions class methods
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions actions = new Actions(driver);
		actions.click(getElement(locator)).build().perform();
	}

	public void moveToElement(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(locator)).build().perform();
	}
	
	/**
	 * this method is used to get the text
	 * @param locator
	 * @return String
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	/**
	 * this method is used to verify element is displayed or not
	 * @param locator
	 * @return boolean true/false
	 */
	public boolean doIsdisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	/**
	 * this method is used to click on specific link from list of links by providing list and link value
	 * @param linkList
	 * @param value
	 */
	public void doLinkClick(List<WebElement> linkList, String value) {
		System.out.println(linkList.size());
		for(WebElement link: linkList) {
			String text=link.getText();
			System.out.println(text);
			if(text.equals(value)) {
				link.click();
				break;
			}
		}
	}
	
//****************************Dropdown Util************************************************
	
	/**
	 * this method is used to select value from drop down by providing visible text
	 * @param locator
	 * @param text
	 */

	public void selectDropdownValueByVisibleText(By locator,String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	/**
	 * this method is used to select value from drop down by providing index
	 * @param locator
	 * @param index
	 */
	public void selectDropdownValueByindex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	/**
	 * this method is used to select value from drop down by providing value
	 * @param locator
	 * @param value
	 */
	public void selectDropdownValueByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	/**
	 * this method is used to get the count of drop down options/values
	 * @param locator
	 * @return int
	 */
	public int getDropdownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		return options.size();
	}
	/**
	 * this method is used to get all the specified dropdown values/options
	 * @param locator
	 * @return List<String>
	 */
	public List<String> getDropdownOptionsValues(By locator) {
		List<String> list = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> options = select.getOptions();
		for (WebElement ele : options) {
			String text = ele.getText();
			list.add(text);
		}
		return list;
	}
	
	/**
	 * this method is used to select value from dropdown when drop down does not have select html tag in HTML DOM
	 * @param locator
	 * @param value
	 */
	public void selectValueFromDropdownWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList = driver.findElements(locator);
		for (WebElement ele : optionsList) {
			String text = ele.getText();
			System.out.println(text);
			if(text.equals(value)) {
				ele.click();
				break;
			}
		}
	}
	
//******************************************Wait Utils*******************************************
	
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public List<WebElement> waitForAllElementsPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public String waitForTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	
	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public List<WebElement> waitForAllElementsVisible(By locator, int timeOut) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public boolean waitForUrl(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver , timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public WebElement waitForElementClickableBylocator(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public WebElement waitForElementClickableByElement(By locator, int timeOut) {
		WebElement element =getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void clickElementWhenReady(By locator, int timeOUt) {
		WebElement element =getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver,timeOUt);
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		ele.click();
	}
	
public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollInterval) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollInterval))
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

//***********************************************************************************************************

/**
 * this method is used to get browser window handles Id
 * @return List<String>
 */
public List<String> getBrowserWindowHandles() {
	Set<String> handles = driver.getWindowHandles();
	List<String> handlesList = new ArrayList<String>(handles);
	return handlesList;
}










	
	
}

