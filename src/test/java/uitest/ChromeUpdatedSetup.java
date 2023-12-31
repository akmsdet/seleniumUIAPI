package uitest;

import common.JavaScriptMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChromeUpdatedSetup {
    WebDriver driver = null;

    @Test
    public void testChrome115() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
/*
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anil.k.mishra\\seleniumDrivers\\chromedriver-win64\\chromedriver.exe");
        options.setBinary("C:\\Users\\anil.k.mishra\\seleniumDrivers\\chrome-win64\\chrome.exe");
        options.addArguments("--user-data-dir=C:\\Users\\anil.k.mishra\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
*/
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        options.addArguments("--start-maximized");
        // To disable Java Socket exception
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(ChromeOptions.CAPABILITY,options);
        driver = new ChromeDriver(options);

        driver.get("https://google.co.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Thread.sleep(5000);
        for (int i = 0; i <= 1000; i = i + 10) {
            JavaScriptMethods.scrollByJS(driver, 0, i);
        }
        driver.quit();
    }

}
