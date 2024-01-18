import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignUpTest {

    @Test
    public void signUpl() {

        // Otworzenie przeglądarki ze stroną do testowania

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        // otworzenie okna przeglądarki na pełnym ekranie
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        // Klikanie na element 'My accounti i 'Sign Up'
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random()*1000);
        String email = "testeroprogramowania" + randomNumber + "@testeroprogramowania.pl";

        // Wypełnianie pól do rejestracji
        driver.findElement(By.name("firstname")).sendKeys("Bartosz");
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Password1234");
        driver.findElement(By.name("confirmpassword")).sendKeys("Password1234");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));
        WebElement welcomeHeader = driver.findElement(By.xpath("//h3[@class='RTL']"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(welcomeHeader.getText().contains(lastname));
    }
}
