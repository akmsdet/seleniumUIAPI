package ui_chrome115;

import base.BasePage;
import common.JavaScriptMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestAllureListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Properties;

@Listeners({TestAllureListener.class})
public class TestPage {
    public BasePage basePage;
    public WebDriver driver;
    public Properties prop;
    public JavaScriptMethods javaScriptMethods = new JavaScriptMethods();

    @BeforeMethod
    public void initSetUp() {
        basePage = new BasePage();
        prop = basePage.initialize_Properties();
        driver = basePage.initialize_driver(prop.getProperty("browserName"));
        driver.get(prop.getProperty("url"));
    }

    @Test(priority = 1, description = "Verify Home page title.", enabled = false)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify Home page title.")
    @Story("Story Name: To check Home page title")
    public void verifyHomePageTitleTest() {
        String title = driver.getTitle();
        System.out.println("the login page title is: " + title);
        Assert.assertEquals(title, "Google");
    }

    @Test(priority = 2, description = "verifying home page title")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify Home page title ")
    @Story("Story Name: To check Home page title")
    public void searchKeyAndVerifyPageTitle() {
        driver.findElement(By.name("q")).sendKeys("Selenium.dev", Keys.ENTER);
        driver.findElement(By.xpath("//a[@href='https://www.selenium.dev/']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("https://www.selenium.dev/"));

        String title = javaScriptMethods.getTitleByJS(driver);
        Assert.assertEquals(title, "Selenium");
    }

    @AfterMethod // --this method will be executed after every test method
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
