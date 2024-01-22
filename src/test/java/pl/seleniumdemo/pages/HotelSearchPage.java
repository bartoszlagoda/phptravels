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

    @FindBy(xpath="//button[@id='adultPlusBtn']")
    private WebElement adultPlusBtn;

    @FindBy(xpath="//button[@id='adultMinusBtn']")
    private WebElement adultMinusBtn;

    @FindBy(xpath = "//input[@id='childInput']")
    private WebElement childInput;

    @FindBy(xpath="//button[@id='childPlusBtn']")
    private WebElement childPlusBtn;

    @FindBy(xpath="//button[@id='childMinusBtn']")
    private WebElement childMinusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getAdultInput() {
        return adultInput;
    }

    public WebElement getChildInput() {
        return childInput;
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

    public void setTravellersByInput(String adultNumber, String childNumber){
        travellers.click();
        adultInput.clear();
        adultInput.sendKeys(adultNumber);
        childInput.clear();
        childInput.sendKeys(childNumber);
    }

    public void setTravellersByBtn(int adultsToAdd, int childToAdd){
        travellers.click();

        changeNumberOfTravellersBtnClick(adultMinusBtn,Integer.parseInt(adultInput.getAttribute("value")));
        changeNumberOfTravellersBtnClick(adultPlusBtn,adultsToAdd);

        changeNumberOfTravellersBtnClick(childMinusBtn,Integer.parseInt(childInput.getAttribute("value")));
        changeNumberOfTravellersBtnClick(childPlusBtn,childToAdd);

    }

    private void changeNumberOfTravellersBtnClick(WebElement travellerBtn, int numberToAdd){
        for (int i=0; i< numberToAdd; i++){
            travellerBtn.click();
        }
    }

    public void performSearch(){
        searchButton.click();
    }
}
