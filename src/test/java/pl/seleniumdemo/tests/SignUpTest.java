package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpHappyPathTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign up Happy Path Test");

        // Stworzenie zmiennych potrzebnych w dalszych krokach testu
        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "testeroprogramowania" + randomNumber + "@testeroprogramowania.pl";

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS,"Opening sign up form", SeleniumHelper.getScreenshot(driver));

        // Wypełnianie pól do rejestracji
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstname("Bartosz");
        test.log(Status.PASS,"Setting first name", SeleniumHelper.getScreenshot(driver));
        signUpPage.setLastname(lastname);
        test.log(Status.PASS,"Setting last name", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPhone("123456789");
        test.log(Status.PASS,"Setting phone number", SeleniumHelper.getScreenshot(driver));
        signUpPage.setEmail(email);
        test.log(Status.PASS,"Setting email", SeleniumHelper.getScreenshot(driver));
        signUpPage.setPassword("Password1234");
        test.log(Status.PASS,"Setting password", SeleniumHelper.getScreenshot(driver));
        signUpPage.setConfirmPassword("Password1234");
        test.log(Status.PASS,"Confirming password", SeleniumHelper.getScreenshot(driver));
        signUpPage.signUp();
        test.log(Status.PASS,"Clicking Sign Up", SeleniumHelper.getScreenshot(driver));
        // sprawdzanie rezultatu logowania
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        wait.withTimeout(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loggedUserPage.getHeadingText().contains(lastname));

        softAssert.assertAll();
        test.log(Status.PASS, "Assertions passed.", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void signUpHappyPathSecondTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign up Happy Path Test [2]");

        // Stworzenie zmiennych potrzebnych w dalszych krokach testu
        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "testeroprogramowania" + randomNumber + "@testeroprogramowania.pl";

        User user = new User();
        user.setFirstName("Bartek");
        user.setLastName("Testowy");
        user.setPhone("111111111");
        user.setEmail(email);
        user.setPassword("Test1234");

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS,"Opening sign up form", SeleniumHelper.getScreenshot(driver));

        // Wypełnianie pól do rejestracji
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillSignUpForm(user);
        test.log(Status.PASS,"Filling all registration fields properly", SeleniumHelper.getScreenshot(driver));
        // sprawdzanie rezultatu logowania
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        wait.withTimeout(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));

        softAssert.assertAll();
        test.log(Status.PASS, "Assertions passed.", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void signUpInvalidEmailTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign up Unhappy Path Test");

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS,"Opening sign up form", SeleniumHelper.getScreenshot(driver));

        String lastname = "Lagoda";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "testeroprogramowania" + randomNumber;

        // Wypełnianie pól do rejestracji
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillSignUpForm("Bartosz", lastname, "123456789", email, "Password1234", "Password1234");
        test.log(Status.PASS,"Filling all registration fields", SeleniumHelper.getScreenshot(driver));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']/p")));

        List<String> dangerAlertsAfterSignIn = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(dangerAlertsAfterSignIn.contains("The Email field must contain a valid email address."));
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(0), "The Email field must contain a valid email address.");

        softAssert.assertAll();
        test.log(Status.PASS, "Assertions passed.", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void signUpUnhappyPathTest() throws IOException {

        ExtentTest test = extentReports.createTest("Sign up Unhappy Path Test [2]");

        // Klikanie na element 'My accounti i 'Sign Up'
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        test.log(Status.PASS,"Opening sign up form", SeleniumHelper.getScreenshot(driver));

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));

        SignUpPage signUpPage = new SignUpPage(driver);
        List<String> dangerAlertsAfterSignIn = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).isDisplayed());
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(0), "The Email field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(1), "The Password field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(2), "The Password field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(3), "The First name field is required.");
        softAssert.assertEquals(dangerAlertsAfterSignIn.get(4), "The Last Name field is required.");

        softAssert.assertAll();
        test.log(Status.PASS, "Assertions passed.", SeleniumHelper.getScreenshot(driver));

    }
}
