package com.qa.ui.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.ui.opencart.exception.FrameworkException;


public class DriverFactory {

	// code for driver initialization

	WebDriver driver;
	Properties prop; // properties is a pre-defined class in java 
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		 String browserName = prop.getProperty("browser");
		//String browserName = System.getProperty("browser");

		System.out.println("browser name is: " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("please pass the right browser name...." + browserName);
			throw new FrameworkException("No Browser Found...");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {

		// mvn clean install -Denv="qa"
		// mvn clean install
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("env name is: " + envName);

		try {
			if (envName == null) {
				System.out.println("your env is null...hence running tests on QA env...");
				ip = new FileInputStream(".\\src\\test\\resource\\Config\\config.qa.properties");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip =  new FileInputStream("./src/test/resource/Config/config.qa.properties");
					System.out.println("Test Execution is in QA environment");
					break;
				case "ist":
					ip =  new FileInputStream("./src/test/resource/Config/config.ist.properties");
					System.out.println("Test Execution is in IST environment");
					break;
				case "sit":
					ip =  new FileInputStream("./src/test/resource/Config/config.sit.properties");
					System.out.println("Test Execution is in SIT environment");
					break;
				case "stage":
					ip =  new FileInputStream("./src/test/resource/Config/config.stage.properties");
					System.out.println("Test Execution is in STAGE environment");
					break;
				case "uat":
					ip =  new FileInputStream("./src/test/resource/Config/config.uat.properties");
					System.out.println("Test Execution is in UAT environment");
					break;
				default:
					System.out.println("Please pass the right environment...."+ envName);
					throw new FrameworkException("WRONG ENVIRONMENT: "+ envName);
				}
			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+".png";
				
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
