package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// NOTE: THIS CLASS IS USED TO LAUNCH AND QUIT THE BROWSER
public class BaseClass {
    public static WebDriver driver;
    public static void setUp() {
        ConfigsReader.loadProperties(Constants.CONFIGURATION_FILEPATH); // Replaced hard-coded filePath with Constants
        switch (ConfigsReader.getProperties("browser").toLowerCase()) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
            }
            case "edge" -> {
                System.setProperty("webdriver.edge.verboseLogging", Constants.EDGE_DRIVER_PATH);
                driver = new EdgeDriver();
            }
            default -> throw new RuntimeException("Browser is not supported");
        }

        driver.get(ConfigsReader.getProperties("url"));

    }

    public static void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        if (driver != null) {     // This line is optional. We only use it to prevent NullPointerException.
        driver.quit();
        }
    }

}
