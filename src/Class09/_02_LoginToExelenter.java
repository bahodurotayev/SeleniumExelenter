package Class09;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class _02_LoginToExelenter {
    public static WebDriver driver;

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = System.getProperty("user.dir") + "/configs/configuration.properties";
        FileInputStream fis = new FileInputStream(filePath);
        Properties properties = new Properties();
        properties.load(fis);

        String browser = properties.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                // "webdriver.geckodriver.driver" --> this one works too, "drivers/gecko.exe" works too, but just for Windows.
                System.setProperty("webdriver.edge.verboseLogging", "/drivers/msedgedriver.exe");
                driver = new EdgeDriver();
            }
        }

        driver.get(properties.getProperty("url"));
        Thread.sleep(3000);
        driver.close();
    }
}
