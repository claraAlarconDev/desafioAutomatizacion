package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

public class CheckoutPage extends ClaseBase {

    //Atributos
    By nombrePasajero = By.xpath("//input[starts-with(@class, 'input-tag traveler-first-name')]");
    By apellidoPasajero = By.xpath("//input[starts-with(@class,'input-tag traveler-last-name')]");
    By numeroDocInput = By.xpath("//input[starts-with(@class, 'input-tag traveler-identification-number')]");
    By emailInput = By.xpath("//input[@automationid='formData.contactData.mainEmailAddress']");
    By emailConfirmInput = By.xpath("//input[@automationid='formData.contactData.repeatMainEmailAddress']");
    By numeroTel = By.xpath("//input[@automationid='phone-number-input-0']");
    By tarjetaDeDebLoc = By.xpath("//span[starts-with(text(), 'Tarjeta de d')]//ancestor::payment-method-selector-radio-button-option");
    By criptoLoc = By.xpath("//span[starts-with(text(), 'Criptomonedas')]//ancestor::payment-method-selector-radio-button-option");
    By tarjetaDeCreLoc = By.xpath("//span[starts-with(text(), 'Tarjeta de cr')]//ancestor::payment-method-selector-radio-button-option");

    By datoNumTarjeta = By.xpath("//input[@name='formData.paymentData.cardPayments[0].card.number']");
    By datoMMAA = By.xpath("//input[@name='formData.paymentData.cardPayments[0].card.expirationDate']");
    By datoCodSeg = By.xpath("//input[@name='formData.paymentData.cardPayments[0].card.securityCode']");
    By datoTitualarTar = By.xpath("//input[@name='formData.paymentData.cardPayments[0].card.holderName']");
    By datoDocTit = By.xpath("//input[@name='formData.paymentData.cardPayments[0].cardHolderIdentification.number']");
    By sexoFem = By.xpath("//em[text()='Femenino']//parent::span");
    By sexoMasc = By.xpath("//em[text()='Masculino']//parent::span");
    By yaCasiText = By.xpath("//h2[contains(text(),'¡Ya casi')]");
    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public void writeForm(String option, String texto){
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
        }
    }

    public void selectMedioPago(String option){
        switch (option){
            case "Tarjeta de Credito":
                click(tarjetaDeCreLoc, 0);
                break;
            case "Tarjeta de Debito":
                click(tarjetaDeDebLoc, 0);
                break;
            case "Criptomonedas":
                click(criptoLoc, 0);
                break;
        }
    }

    public void datosTarjeta(String num, String mmAa, String codSeg, String titular, String docTit, String sexo, int seg){
        write(datoNumTarjeta, seg, num);
        write(datoMMAA, seg, mmAa);
        write(datoCodSeg, seg, codSeg);
        write(datoTitualarTar, seg, titular);
        write(datoDocTit, seg, docTit);
        switch (sexo){
            case "Femenino":
                click(sexoFem, seg);
                break;
            case "Masculino":
                click(sexoMasc, seg);
                break;
        }
    }

    public boolean estoyEnCheckout(){
        return elementExists(yaCasiText, 0);
    }
}
