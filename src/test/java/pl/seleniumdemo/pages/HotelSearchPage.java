package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Strona główna z wyszukiwaniem
public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelspan;

    @FindBy(xpath = "//div[@id='select2-drop']/div/input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//div[@class='select2-result-label']/span[@class='select2-match' and contains(text(),'Dubai')]")
    private WebElement hotelMatch;

    @FindBy(name = "checkin")
    private WebElement checkInInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(name = "travellers")
    private WebElement travellers;

    @FindBy(xpath = "//input[@id='adultInput']")
    private WebElement adultInput;

    @FindBy(xpath = "//input[@id='childInput']")
    private WebElement childInput;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void setCity(String cityName){
        searchHotelspan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
    }

    public void setTravelDate(String checkin, String checkout){
        checkInInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
    }

    public void setTravellers(String adultNumber, String childNumber){
        travellers.click();
        adultInput.sendKeys(adultNumber);
        childInput.sendKeys(childNumber);
    }

    public void performSearch(){
        searchButton.click();
    }
}
