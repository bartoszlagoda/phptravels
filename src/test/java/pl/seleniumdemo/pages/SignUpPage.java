package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    public void fillSignUpForm(String firstName, String lastname, String phone, String email, String password, String confirmPassword){
        setFirstname(firstName);
        setLastname(lastname);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        signUp();
    }

    public void fillSignUpForm(User user){
        setFirstname(user.getFirstName());
        setLastname(user.getLastName());
        setPhone(user.getPhone());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setConfirmPassword(user.getPassword());
        signUp();
    }

    public List<String> getErrors(){
        return alertDanger
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public void signUp(){
        signUpBtn.click();
    }
}
