package testBase;

import io.github.bonigarcia.wdm.WebDriverManager; // <-- Import WebDriverManager
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utility.ConfigsReader;
import utility.Constants;

import java.time.Duration; // <-- Use modern Duration instead of TimeUnit

public class BaseClass extends TestData{

    public static WebDriver driver;

    public static WebDriver setUp(){
        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigsReader.getProperty("browser").toLowerCase()){
            case "chrome":
                // WebDriverManager handles everything for you!
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;

            case "firefox" :
                // You can use it for Firefox, too!
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser is not supported");
        }

        // Updated to use modern Duration for timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
        driver.manage().window().maximize();
        driver.get(ConfigsReader.getProperty("url"));

        PageInitializer.initialize();

        return driver;
    }

    public static void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}