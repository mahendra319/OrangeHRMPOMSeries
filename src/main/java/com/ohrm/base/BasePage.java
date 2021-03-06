package com.ohrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ohrm.utils.BrowserOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author mahen
 *
 */
public class BasePage {

	WebDriver driver;
	Properties prop;
	BrowserOptions browserOptions;
		
	public static String flashElement;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * this is method is used to intialize driver based on given browser
	 * @param prop
	 * @return it will return driver
	 */
	public WebDriver initialize_Driver(Properties prop) {
		
		browserOptions = new BrowserOptions(prop);
		flashElement = prop.getProperty("flash").trim();
		
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("Browser is: "+browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote").trim())) {
				initialize_RemoteWebDriver(browserName);
			}else {
			//driver = new ChromeDriver(browserOptions.getChromeOptions());
			tlDriver.set(new ChromeDriver(browserOptions.getChromeOptions()));
			}
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote").trim())) {
				initialize_RemoteWebDriver(browserName);
			}else {
			//driver = new FirefoxDriver(browserOptions.getFirefoxOption());
			tlDriver.set(new FirefoxDriver(browserOptions.getFirefoxOption()));
			}
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote").trim())){
				initialize_RemoteWebDriver(browserName);
			}else {
			//driver = new EdgeDriver(browserOptions.getEdgeOptions());
			tlDriver.set(new EdgeDriver(browserOptions.getEdgeOptions()));
			}
		}else {
			System.out.println("Please check the browser name: "+browserName);
		}
		
		//driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
		//driver.manage().window().maximize();
		getDriver().manage().window().maximize();
		//driver.get(prop.getProperty("url").trim());
		getDriver().get(prop.getProperty("url").trim());
		
		
		return getDriver();
	}
	
	/**
	 * this method is used to get the WebDriver
	 * @return
	 */
	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method will define the desired capabilities and will initialize driver with capabilities
	 * Also, this method will initialize driver with selenium HUB/Port
	 * @param browserName
	 */
	public void initialize_RemoteWebDriver(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, browserOptions.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl").trim()), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, browserOptions.getFirefoxOption());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl").trim()), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//DesiredCapabilities cap = DesiredCapabilities.edge();
			//cap.setCapability(EdgeOptions.class., browserOptions.getEdgeOptions());
		}
		
	}
	
	
	
	
	/**
	 * this method is used to intialize properties 
	 * @return prop
	 */
		public Properties intialize_Properties() {
			
			prop = new Properties();
			String path = null;
			String env = null;
				
			try {
				env = System.getProperty("env");
				System.out.println("Running on Environment is: "+env);
				
				if(env == null) {
					path = ".\\src\\main\\java\\com\\ohrm\\config\\config.properties";
					System.out.println("Environment is : PROD");
				}else {
					switch (env) {
					case "qa":
						path = ".\\src\\main\\java\\com\\ohrm\\config\\config.qa.properties";
						break;
					case "dev":
						path = ".\\src\\main\\java\\com\\ohrm\\config\\config.dev.properties";
						break;
					case "stage":
						path = ".\\src\\main\\java\\com\\ohrm\\config\\config.stage.properties";
						break;	
						
					default:
						System.out.println("Please enter correct enviroment");
						break;
					}
				}
				
				FileInputStream ip = new FileInputStream(path);
				prop.load(ip);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return prop;
		}
	

		/**
		 * this mehtod is used to take screenshot
		 * @return it will return path 
		 */
		public String getScreenshot() {
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
