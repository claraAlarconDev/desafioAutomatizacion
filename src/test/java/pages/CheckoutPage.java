package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

public class CheckoutPage extends ClaseBase {

    //Atributos
    By nombrePasajero = By.xpath("//input[starts-with(@class, 'input-tag traveler-first-name')]");
    By apellidoPasajero = By. xpath("input-tag traveler-last-name");
    By numeroDocInput = By.xpath("//input[starts-with(@class, 'input-tag traveler-identification-number')]");
    By emailInput = By.xpath("//input[@automationid='formData.contactData.mainEmailAddress']");
    By emailConfirmInput = By.xpath("//input[@automationid='formData.contactData.repeatMainEmailAddress']");
    By numeroTel = By.xpath("//input[@automationid='phone-number-input-0']");
    By tarjetaDeDebLoc = By.xpath("//span[starts-with(text(), 'Tarjeta de d')]//ancestor::payment-method-selector-radio-button-option");
    By criptoLoc = By.xpath("//span[starts-with(text(), 'Criptomonedas')]//ancestor::payment-method-selector-radio-button-option");
    By tarjetaDeCreLoc = By.xpath("//span[starts-with(text(), 'Tarjeta de cr')]//ancestor::payment-method-selector-radio-button-option");
    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public void write(String texto, String option){
        switch(option) {
            case "Nombre Pasajero":
                write(nombrePasajero, 0, texto);
                break;
            case "Apellido Pasajero":
                write(apellidoPasajero, 0, texto);
                break;
            case "Numero documento":
                write(numeroDocInput, 0, texto);
                break;
            case "Email":
                write(emailInput, 0, texto);
                break;
            case "Email confirmacion":
                write(emailConfirmInput, 0, texto);
                break;
            case "Numero telefono":
                write(numeroTel, 0, texto);
                break;
            case "Tarjeta de Credito":
                write(tarjetaDeCreLoc, 0, texto);
                break;
            case "Tarjeta de Debito":
                write(tarjetaDeDebLoc, 0, texto);
                break;
            case "Criptomonedas":
                write(criptoLoc, 0, texto);
                break;
        }
    }
}
