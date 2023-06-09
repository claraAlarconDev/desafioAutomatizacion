package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

public class ArrepentimientoPage extends ClaseBase {

    By emailInput = By.xpath("//input[@placeholder='Email']");
    By numReservaInput = By.xpath("//input[@placeholder='N° de reserva']");
    By alertEmail = By.xpath("//span[@class='validation-msg' and contains(text(), 'email')]");
    By alertReserva = By.xpath("//span[@class='validation-msg' and contains(text(), 'reserva')]");
    By continuarBtn = By.xpath("//em[@id='retraction_confirm']//parent::button");
    By exitoTexto = By.xpath("//span[contains(text(),'¡Listo! Recibimos tu solicitud de arrepentimiento de compra.')]");

    public ArrepentimientoPage(WebDriver driver){
        super(driver);
    }
    public void pressContinuar(int seg){
        click(continuarBtn, seg);
    }

    public void fillEmail(String email, int seg){
        write(emailInput, seg, email);
    }

    public void fillNumReserva(int seg, String numReserva){
        write(numReservaInput, seg, numReserva);
    }

    public boolean noEmail(int seg){
       return elementExists(alertEmail, seg);
    }

    public boolean noReserva(int seg){
        return elementExists(alertReserva, seg);
    }

    public boolean success(int seg){
        String texto= getText(exitoTexto, seg);
        return (texto != null)? true: false;
    }
}
