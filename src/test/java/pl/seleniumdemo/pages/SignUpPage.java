package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public SignUpPage (WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void setFirstname(String firstname){
        firstnameInput.sendKeys(firstname);
    }

    public void setLastname(String lastname){
        lastnameInput.sendKeys(lastname);
    }

    public void setPhone(String phone){
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email){
        emailInput.sendKeys(email);
    }

    public void setPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword){
        confirmpasswordInput.sendKeys(confirmPassword);
    }

    public void signUp(){
        signUpBtn.click();
    }
}
