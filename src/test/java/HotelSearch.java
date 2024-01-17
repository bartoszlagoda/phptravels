import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HotelSearch {

    @Test
    public void searchHotel() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // otworzenie okna przeglądarki na pełnym ekranie
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        WebElement searchByCityName = driver.findElement(By.className("select2-chosen"));
        searchByCityName.click();
        WebElement inputCityName = driver.findElement(By.xpath("//div[@id='select2-drop']/div/input"));
        inputCityName.sendKeys("Dubai");
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        // (WebDriverWait to ma wbudowane)
        wait.ignoring(NoSuchElementException.class); // dodanie ignorowania NoSuchElementException
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(1)); // co 1 sekundę odpytuj
        By dubaiLocatorChoosen = By.xpath("//div[@class='select2-result-label']/span[@class='select2-match' and contains(text(),'Dubai')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dubaiLocatorChoosen));
        driver.findElement(dubaiLocatorChoosen).click();

    }
}
