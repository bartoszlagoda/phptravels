package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        // Wypełnienie pola 'Search by Hotel or City Name'
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setTravelDate("29/01/2024","02/02/2024");
        hotelSearchPage.setTravellers("2","2");
        hotelSearchPage.performSearch();

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(adults.getAttribute("value"),"2");
//        softAssert.assertEquals(child.getAttribute("value"),"2");

//         przejście do strony filter search
        List<String> hotelNames = resultsPage.getHotelNames();

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

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        // Wybranie daty przyjazdu i odjazdu
        hotelSearchPage.setTravelDate("22/02/2024","26/02/2024");

        // Wybranie liczby osób, które mają wyjechać

        hotelSearchPage.setTravellers("2","3");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hotelSearchPage.getAdultInput().getAttribute("value"),"2");
        softAssert.assertEquals(hotelSearchPage.getChildInput().getAttribute("value"),"3");

        // kliknięcie przycisku Search
        hotelSearchPage.performSearch();

        // przejście do strony filter search
        List<String> hotelNames = driver.findElements(By.xpath("//h4[@class='RTL go-text-right mt0 mb4 list_title']//b")).stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        WebElement noResultsHeading = driver.findElement(By.xpath("//div[@class='itemscontainer']/h2"));

        softAssert.assertTrue(resultsPage.getHotelNames().size() == 0);
        softAssert.assertTrue(noResultsHeading.isDisplayed());
        softAssert.assertEquals(noResultsHeading.getAttribute("textContent"),"No Results Found");

        softAssert.assertAll();
    }
}
