package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.time.Duration;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpHappyPathTest() {

        // klikanie na element MyAccount i SignUp oraz Wypełnianie pól do rejestracji
        LoggedUserPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstname("Bartosz")
                .setLastname("Lagoda")
                .setPhone("123456789")
                .setEmail("testeroprogramowania" + (int) (Math.random()*1000) + "@testeroprogramowania.pl")
                .setPassword("Password1234")
                .setConfirmPassword("Password1234")
                .signUp();
        // sprawdzanie rezultatu logowania
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        wait.withTimeout(Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='RTL']")));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loggedUserPage.getHeadingText().contains("Lagoda"));

        softAssert.assertAll();
    }

    @Test
    public void signUpUnhappyPathTest() {

        // Klikanie na element 'My accounti i 'Sign Up'
        LoggedUserPage hotelSearchPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .signUp();

        SignUpPage signUpPage = new SignUpPage(driver);
        List<String> dangerAlertsAfterSignIn = signUpPage.getErrors();

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
