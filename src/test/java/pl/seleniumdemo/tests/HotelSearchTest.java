package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

    @Test
    public void searchHotelTest() {

        // Wypełnienie pola 'Search by Hotel or City Name'
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setTravelDate("29/01/2024","02/02/2024");
        hotelSearchPage.setTravellers("2","2");
        hotelSearchPage.performSearch();

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(adults.getAttribute("value"),"2");
//        softAssert.assertEquals(child.getAttribute("value"),"2");

//         przejście do strony filter search
        List<String> hotelNames = driver.findElements(By.xpath("//h4[@class='RTL go-text-right mt0 mb4 list_title']//b")).stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        System.out.println(hotelNames.size());
//        hotelNames.forEach(el -> System.out.println(el));
        hotelNames.forEach(System.out::println); // dla każdego elementu hotelNames wykonaj sout
//
        softAssert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        softAssert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        softAssert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        softAssert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

        softAssert.assertAll();
    }

    @Test
    public void searchNotFoundInvolvedTest(){

        // Wybranie daty przyjazdu i odjazdu

        WebElement checkInOnMainPage = driver.findElement(By.name("checkin"));
        checkInOnMainPage.sendKeys("20/01/2024"); // wpisanie daty
        // wyklikanie daty wyjazdu
        WebElement checkOutOnMainPage = driver.findElement(By.name("checkout"));
        checkOutOnMainPage.click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='25']"))
                .stream()
//                        .filter(el -> el.isDisplayed())
                .filter(WebElement::isDisplayed)
                .findFirst()
//                        .ifPresent(el -> el.click());
                .ifPresent(WebElement::click);

        // Wybranie liczby osób, które mają wyjechać

        WebElement travellers = driver.findElement(By.name("travellers"));
        travellers.click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("adults"))));
        WebElement adults = driver.findElement(By.name("adults"));
        adults.clear();
        adults.sendKeys("2");
        WebElement childsPlusBtn = driver.findElement(By.xpath("//button[@id='childPlusBtn']"));
        childsPlusBtn.click();
        childsPlusBtn.click();
        WebElement child = driver.findElement(By.xpath("//input[@id='childInput']"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(adults.getAttribute("value"),"2");
        softAssert.assertEquals(child.getAttribute("value"),"2");

        // kliknięcie przycisku Search
        WebElement searchBtn = driver.findElement(By.xpath("//div[@class='col-md-2 form-group go-right col-xs-12 search-button']/button[@type='submit']"));
        searchBtn.click();

        // przejście do strony filter search
        List<String> hotelNames = driver.findElements(By.xpath("//h4[@class='RTL go-text-right mt0 mb4 list_title']//b")).stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        WebElement noResultsHeading = driver.findElement(By.xpath("//div[@class='itemscontainer']/h2"));

        softAssert.assertTrue(hotelNames.size() == 0);
        softAssert.assertTrue(noResultsHeading.isDisplayed());
        softAssert.assertEquals(noResultsHeading.getAttribute("textContent"),"No Results Found");

        softAssert.assertAll();
    }
}
