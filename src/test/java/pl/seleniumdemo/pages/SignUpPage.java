package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstnameInput;

    @FindBy(name = "lastname")
    private WebElement lastnameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
    private List<WebElement> alertDanger;

    private WebDriver driver;

    public SignUpPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public SignUpPage setFirstname(String firstname){
        firstnameInput.sendKeys(firstname);
        return this;
    }

    public SignUpPage setLastname(String lastname){
        lastnameInput.sendKeys(lastname);
        return this;
    }

    public SignUpPage setPhone(String phone){
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage setConfirmPassword(String confirmPassword){
        confirmpasswordInput.sendKeys(confirmPassword);
        return this;
    }

    public List<String> getErrors(){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));
        return alertDanger
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public LoggedUserPage signUp(){
        signUpBtn.click();
        return new LoggedUserPage(driver);
    }
}
