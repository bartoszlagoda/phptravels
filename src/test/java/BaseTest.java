import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;

    @BeforeMethod
    public void setup(){
        // Otworzenie przeglądarki ze stroną do testowania

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new FluentWait<>(driver);
        // otworzenie okna przeglądarki na pełnym ekranie
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
