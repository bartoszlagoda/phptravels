package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

// Strona główna z wyszukiwaniem
public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelspan;

    @FindBy(xpath = "//div[@id='select2-drop']/div/input")
    private WebElement searchHotelInput;

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

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;

    private WebDriver driver;

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public WebElement getAdultInput() {
        return adultInput;
    }

    public WebElement getChildInput() {
        return childInput;
    }

    public HotelSearchPage setCity(String cityName){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Search by Hotel or City Name']")));
        searchHotelspan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//div[@class='select2-result-label']/span[@class='select2-match' and contains(text(),'%s')]",cityName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public HotelSearchPage setTravelDate(String checkin, String checkout){
        checkInInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        return this;
    }

    public HotelSearchPage setTravellersByInput(String adultNumber, String childNumber){
        travellers.click();
        adultInput.clear();
        adultInput.sendKeys(adultNumber);
        childInput.clear();
        childInput.sendKeys(childNumber);
        return this;
    }

    public HotelSearchPage setTravellersByBtn(int adultsToAdd, int childToAdd){
        travellers.click();

        changeNumberOfTravellersBtnClick(adultMinusBtn,Integer.parseInt(adultInput.getAttribute("value")));
        changeNumberOfTravellersBtnClick(adultPlusBtn,adultsToAdd);

        changeNumberOfTravellersBtnClick(childMinusBtn,Integer.parseInt(childInput.getAttribute("value")));
        changeNumberOfTravellersBtnClick(childPlusBtn,childToAdd);

        return this;
    }

    private void changeNumberOfTravellersBtnClick(WebElement travellerBtn, int numberToAdd){
        for (int i=0; i< numberToAdd; i++){
            travellerBtn.click();
        }
    }

    public SignUpPage openSignUpForm(){
        myAccountLink
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        signUpLink.get(1).click();

        return new SignUpPage(driver);
    }
    // uruchomienie metody spowoduje przejście do nowej strony, którą obsługuje ResultPage
    public ResultsPage performSearch(){
        searchButton.click();
        return new ResultsPage(driver);
    }
}
