package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import org.testng.Reporter;

import com.guru99bank.web.LoginPage;

public class base {

	public static WebDriver driver;
	public static Properties prop;
	public static LoginPage loginPage;
	public static Logger log = LogManager.getLogger(base.class);
	public static Calendar calender;
	public static SimpleDateFormat sdf;

	public static WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"src\\main\\java\\resources\\globaldata.properties");

		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("browserExe"));
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("browserExe"));
			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", prop.getProperty("browserExe"));
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver setup() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("baseurl"));
		log.info("------------------------------------------------------------------------------------");
		log.info("Browser initialized");
		Reporter.log("Browser initialized and rediredcted to page successfully");
		loginPage = new LoginPage(driver);
		loginPage.userID().sendKeys(prop.getProperty("mgrID"));
		log.info("UserID entered");
		loginPage.password().sendKeys(prop.getProperty("mgrPwd"));
		log.info("Password entered");
		loginPage.loginBtn().click();
		log.info("Clicked on login button");
		Reporter.log("Browser initialized and rediredcted to page successfully");
		return driver;
	}

	public static void teardown() {
		driver.quit();
		driver = null;
		log.info("Browser instance closed");
	}

	public static void getScreenshot(String result) throws IOException {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dst = new File(
				prop.getProperty("screenshotPath") + result + "_" + current.format(format) + "_screenshot.png");
		FileUtils.copyFile(src, dst);
	}

	public static String getData() {
		calender = Calendar.getInstance();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calender.getTime());
	}
	
	public static Alert getAlert()
	{
		Alert Alert = driver.switchTo().alert();
		return Alert;
	}
}
