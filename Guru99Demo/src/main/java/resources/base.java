package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.guru99bank.web.LoginPage;

public class base {

	private WebDriver driver;
	public static Properties prop;
	private LoginPage loginPage;
	private static Logger log = LogManager.getLogger(base.class);
	private Calendar calender;
	private SimpleDateFormat sdf;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\globalconfig\\globaldata.properties");

		prop.load(fis);
		// String browserName = System.getProperty("browser");

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();

		} else if (browserName.equals("safari")) {

			//

		} else if (browserName.equals("headless")) {
			driver = new HtmlUnitDriver(true);

		} else {

			log.error("Please provide valid browser name (chrome | firefox | edge | safari | headless)");
			System.out.println("Please provide valid browser name (chrome | firefox | edge | safari | headless)");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver appLogin() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("baseurl"));
		loginPage = new LoginPage(driver);
		loginPage.userID().sendKeys(prop.getProperty("mgrID"));
		log.info("UserID entered");
		loginPage.password().sendKeys(prop.getProperty("mgrPwd"));
		log.info("Password entered");
		loginPage.loginBtn().click();
		return driver;
	}

	public void teardown() {
		driver.quit();
		driver = null;
		log.info("Browser instance closed");
	}

	public void getScreenshot(String result) throws IOException {
		LocalDateTime current = LocalDateTime.now(); // Get current time

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");// convert to desired format

		// convert driver object to TakesScreenshot and use getScreenshotAs method to
		// create image
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Set location to save file
		File dst = new File(".\\screenshots\\" + result + "_" + current.format(format) + "_screenshot.png");

		// Copy file to desired location
		FileUtils.copyFile(src, dst);

	}

	public String getDate() {
		calender = Calendar.getInstance();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calender.getTime());
	}

	public Alert getAlert() {
		Alert Alert = driver.switchTo().alert();
		return Alert;
	}

	public void getConsoleErrors() {
		LogEntries LogEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : LogEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}

	}

	public String getTitle() {
		return driver.getTitle();
	}
}
