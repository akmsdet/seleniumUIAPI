package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BasePage {

    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
    public WebDriver driver;
    public Properties prop;

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    public WebDriver initialize_driver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
 ChromeOptions options = new ChromeOptions();
           /* System.setProperty("webdriver.chrome.driver", "C:\\Users\\paran\\seleniumDrivers\\chromedriver-win64\\chromedriver.exe");
           
            options.setBinary("C:\\Users\\paran\\seleniumDrivers\\chrome-win64\\chrome.exe");

            options.addArguments("--user-data-dir=C:\\Users\\paran\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
*/
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("incognito");
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("start-maximized");
            driver = new EdgeDriver(options);
        } else System.err.println("Invalid BrowserName passed" + browserName);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().deleteAllCookies();
        tdriver.set(driver);
        return getDriver();
    }

    public Properties initialize_Properties() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
        return path;
    }

}
