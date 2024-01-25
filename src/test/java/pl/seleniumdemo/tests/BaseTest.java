package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.seleniumdemo.utils.DriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentSparkReporter extentSparkReporter;
    protected static ExtentReports extentReports;
    protected FluentWait<WebDriver> wait;

    @BeforeSuite
    public void beforeSuite(){
        extentSparkReporter = new ExtentSparkReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    @AfterSuite
    public void afterSuite(){
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() throws IOException {
        // Otworzenie przeglądarki ze stroną do testowania

        driver = DriverFactory.getDriver();
        wait = new FluentWait<>(driver);
        // otworzenie okna przeglądarki na pełnym ekranie
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
