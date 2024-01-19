package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    @FindBy(xpath = "//h4[@class='RTL go-text-right mt0 mb4 list_title']//b")
    private List<WebElement> hotelList;

    @FindBy(xpath = "//div[@class='itemscontainer']/h2")
    private List<WebElement> resultHeading;

    public ResultsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public List<String> getHotelNames(){
        return hotelList.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }
}
