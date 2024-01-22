package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpHappyPathTest() {

        // Stworzenie zmiennych potrzebnych w dalszych krokach testu
        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random()*1000);
        String email = "testeroprogramowania" + randomNumber + "@testeroprogramowania.pl";

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        // Wypełnianie pól do rejestracji
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstname("Bartosz");
        signUpPage.setLastname(lastname);
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Password1234");
        signUpPage.setConfirmPassword("Password1234");
        signUpPage.signUp();
        // sprawdzanie rezultatu logowania
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        wait.withTimeout(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));

        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmailTest() {

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random()*1000);
        String email = "testeroprogramowania" + randomNumber;

        // Wypełnianie pól do rejestracji
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstname("Bartosz");
        signUpPage.setLastname(lastname);
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Password1234");
        signUpPage.setConfirmPassword("Password1234");
        signUpPage.signUp();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']/p")));

        List<String> dangerAlertsAfterSignIn = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(dangerAlertsAfterSignIn.contains("The Email field must contain a valid email address."));
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(0),"The Email field must contain a valid email address.");

        softAssert.assertAll();
    }

    @Test
    public void signUpUnhappyPathTest() {

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

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
