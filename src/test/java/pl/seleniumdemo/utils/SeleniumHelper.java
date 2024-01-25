package pl.seleniumdemo.utils;

import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.MediaEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumHelper {

    public static void waitForElementToExist(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForNotEmptyList(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(browser -> driver.findElements(locator).size() > 0);
    }

    //stworzenie screenshota i dodanie go do raportu
    public static Media getScreenshot( WebDriver driver) throws IOException {
        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }

    private static String takeScreenshot(WebDriver driver) throws IOException {
        // stworzenie randomowego numeru od 0 do 1000
        int randomNumber = (int) (Math.random()*1000);
        // utworzenie obiektu interfejsu TakeScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        // zadeklarowanie screenshota do pliku
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        // podanie nazwy dla tworzonego pliku
        String path = "src/test/resources/screenshots/screenshot" + randomNumber + ".png";
        // przeniesienie utworzonego pliku do podanej ścieżki
        FileUtils.copyFile(file, new File(path));
        return path;
    }
}
