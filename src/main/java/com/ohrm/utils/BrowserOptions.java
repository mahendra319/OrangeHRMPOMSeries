package com.ohrm.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {

	ChromeOptions co;
	FirefoxOptions fo;
	EdgeOptions eo;
	Properties prop;
	
	public BrowserOptions(Properties prop) {
		this.prop = prop;
	}
	
	
	
	public ChromeOptions getChromeOptions() {
		
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		
		return co;
	}
	
	public FirefoxOptions getFirefoxOption() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}
		
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			
		}
		
		return eo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
