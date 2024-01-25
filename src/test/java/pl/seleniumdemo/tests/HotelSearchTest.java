package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        ExtentTest test = extentReports.createTest("Search Hotel Test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        // Wypełnienie pola 'Search by Hotel or City Name'
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city 'Dubai' succesfully");
        hotelSearchPage.setTravelDate("29/01/2024", "02/02/2024");
        test.log(Status.PASS, "Setting dates done");
        hotelSearchPage.setTravellersByInput("2", "2");
        test.log(Status.PASS, "Setting travellers done");
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done");
        List<String> hotelNames = resultsPage.getHotelNames();


        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(adults.getAttribute("value"),"2");
//        softAssert.assertEquals(child.getAttribute("value"),"2");

//         przejście do strony filter search


        System.out.println(hotelNames.size());
//        hotelNames.forEach(el -> System.out.println(el));
        hotelNames.forEach(System.out::println); // dla każdego elementu hotelNames wykonaj sout
//
        softAssert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        softAssert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        softAssert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        softAssert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

        softAssert.assertAll();
        test.log(Status.PASS,"Assertions passed.");
    }

    @Test
    public void searchNotFoundInvolvedTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        // Wybranie daty przyjazdu i odjazdu
        hotelSearchPage.setTravelDate("22/02/2024", "26/02/2024");

        // Wybranie liczby osób, które mają wyjechać

//        hotelSearchPage.setTravellersByInput("2","3");
        hotelSearchPage.setTravellersByBtn(3, 1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hotelSearchPage.getAdultInput().getAttribute("value"), "3");
        softAssert.assertEquals(hotelSearchPage.getChildInput().getAttribute("value"), "1");

        // kliknięcie przycisku Search
        hotelSearchPage.performSearch();

        // przejście do strony filter search
        resultsPage.getHotelNames();

        softAssert.assertTrue(resultsPage.getHotelNames().size() == 0);
        softAssert.assertEquals(resultsPage.getHeadingText(), "No Results Found");

        softAssert.assertAll();
    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        // Wypełnienie pola 'Search by Hotel or City Name'
        List<String> hotelNames = hotelSearchPage.setCity(city)
                .setTravelDate("29/01/2024", "02/02/2024")
                .setTravellersByInput("2", "2")
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
        softAssert.assertEquals(hotel, hotelNames.get(0));

        softAssert.assertAll();
    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcelFile("testData.xlsx");
    }
}
