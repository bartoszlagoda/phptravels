import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseAllMethodsTest {

    @Test
    public void signUpHappyPathTest() {

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

        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmailTest() {

        // Klikanie na element 'My accounti i 'Sign Up'
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random()*1000);
        String email = "testeroprogramowania" + randomNumber;

        // Wypełnianie pól do rejestracji
        driver.findElement(By.name("firstname")).sendKeys("Bartosz");
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Password1234");
        driver.findElement(By.name("confirmpassword")).sendKeys("Password1234");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']/p")));

        List<String> dangerAlertsAfterSignIn = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(dangerAlertsAfterSignIn.contains("The Email field must contain a valid email address."));
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(0),"The Email field must contain a valid email address.");

        softAssert.assertAll();
    }

    @Test
    public void signUpUnhappyPathTest() {

        // Klikanie na element 'My accounti i 'Sign Up'
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));
        List<String> dangerAlertsAfterSignIn = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).isDisplayed());
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(0),"The Email field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(1),"The Password field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(2),"The Password field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(3),"The First name field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(4),"The Last Name field is required.");

        softAssert.assertAll();

    }
}
