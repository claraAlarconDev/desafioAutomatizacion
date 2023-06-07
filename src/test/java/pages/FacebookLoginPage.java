package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilidades.ClaseBase;

public class FacebookLoginPage extends ClaseBase {

    public FacebookLoginPage(WebDriver driver){
        super(driver);
    }

    //Locators
    By emailORPhoneInput = By.xpath("//input[@id='email']");
    By passwordLocator = By.xpath("//input[@id='pass']");

    By iniciarSesionBtn = By.xpath("//input[@name='login' and @type='submit']");

    public void fillEmail(String email){
        write(emailORPhoneInput, 0, email);
    }

    public void fillPass(String pass){
        write(passwordLocator, 0, pass);
    }

    public void pressIniciarSesion(){
        click(iniciarSesionBtn, 0);
    }

}
