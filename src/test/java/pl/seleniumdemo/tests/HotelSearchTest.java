package pl.seleniumdemo.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        // Wypełnienie pola 'Search by Hotel or City Name'
        List<String> hotelNames = hotelSearchPage.setCity("Dubai")
                .setTravelDate("29/01/2024","02/02/2024")
                .setTravellersByInput("2","2")
                .performSearch()
                .getHotelNames();

        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(adults.getAttribute("value"),"2");
//        softAssert.assertEquals(child.getAttribute("value"),"2");

//         przejście do strony filter search


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
    public void searchNotFoundInvolvedTest() {

        SoftAssert softAssert = new SoftAssert();

        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setTravelDate("22/02/2024","26/02/2024")
                .setTravellersByBtn(3,1)
                .performSearch();

        softAssert.assertTrue(resultsPage.getHotelNames().size() == 0);
        softAssert.assertEquals(resultsPage.getHeadingText(),"No Results Found");

        softAssert.assertAll();
    }
}
