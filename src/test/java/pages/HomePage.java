package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utilidades.ClaseBase;

import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends ClaseBase {

    private String url = "https://www.despegar.com.ar/";

    //Constructor
    public HomePage(WebDriver driver){
        super(driver);
    }

    //Identificar localizadores

    By origenInputsLocator = By.xpath("//input[@placeholder='Ingresa desde dónde viajas']");
    // By origenInputT1Locator = By.xpath("//span[contains(text(), 'Tramo 1')]//following-sibling::div[@class='sbox5-places-component--1i-wZ']//child::input[starts-with(@placeholder, 'Ingresa desde')]")
    By origenInputT1Locator = By.xpath("//span[contains(text(), 'Tramo 1')]//following-sibling::div//child::input[starts-with(@placeholder, 'Ingresa desde')]");
    By origenInputT2Locator = By.xpath("//span[contains(text(), 'Tramo 2')]//following-sibling::div//child::input[starts-with(@placeholder, 'Ingresa desde')]");
    By destinoInputT1Locator = By.xpath("//span[contains(text(), 'Tramo 1')]//following-sibling::div//child::input[starts-with(@placeholder, 'Ingresa hacia')]");
    By destinoInputT2Locator = By.xpath("//span[contains(text(), 'Tramo 2')]//following-sibling::div//child::input[starts-with(@placeholder, 'Ingresa hacia')]");

    By noBenefitsBtn = By.xpath("//em[contains(text(), 'No quiero beneficios')]//parent::span[@class='login-incentive--button login-incentive--button-close shifu-3-btn-ghost']");
    By entendiBtnLocator = By.xpath("//div[@class='lgpd-banner']//descendant::em[contains(text(), 'Entend')]//parent::a");

    //Acciones del page

    public void go(){
        loadPage(url);
    }

    public void selectNoBenefits(int seg){
        click(noBenefitsBtn, seg);
    }

    public void selectFlightOption(String option, int seg) {
        String[] tipoVuelos = {"ida y vuelta", "Solo ida", "Multidestino", "Vuelo + Alojamiento"};
       /* switch(option){
            case "Ida y vuelta":
                click(idaYVueltaBTNLocator, seg);
                break;
            case "Solo ida":
                click(soloIdaBTNLocator, seg);
                break;
            case "Multidestino":
                click(multidestinoBTNLocator,seg);
                break;
            case "Vuelo + Alojamiento":
                click(vueloPlusAlojLocator,seg);
                break;
        }*/
       /* if(option == "Ida y vuelta"){
            click(idaYVueltaBTNLocator, seg);
        } else if(option == "Solo ida"){
            click(soloIdaBTNLocator, seg);
        } else if(option == "Multidestino"){
            click(multidestinoBTNLocator,seg);
        } else if(option == "Vuelo + Alojamiento"){
            click(vueloPlusAlojLocator,seg);
        }*/
        String element = "";
        int i = 0;
        while (element != option && i < tipoVuelos.length) {
            element = tipoVuelos[i];
            if(element.equalsIgnoreCase(option)){
                By locator =  By.xpath("//em[contains(text(), '"+element+"')]//parent::button");
                click(locator, seg);
            }
            i++;
        }
    }

    public void writeOrigen(String origen, String tramo, int seg ){

        if(tramo.equalsIgnoreCase("Tramo 1")) {
            write(origenInputT1Locator, seg, origen);

        } else if(tramo.equalsIgnoreCase("Tramo 2")){
            write(origenInputT2Locator, seg, origen);

        }

    }

    public void writeDestino(String destino, String tramo, int seg ){
        if(tramo.equalsIgnoreCase("Tramo 1")) {
            write(destinoInputT1Locator, seg, destino);

        } else if(tramo.equalsIgnoreCase("Tramo 2")){
            write(destinoInputT2Locator, seg, destino);

        }

    }

    public boolean validateTextoInput(String elemento, String texto, int seg) {
        switch(elemento){
            case "origen t1":
                return validateText(origenInputT1Locator, seg, texto);
            case "origen t2":
                return validateText(origenInputT2Locator, seg, texto);
            case "destino t1":
                return validateText(destinoInputT1Locator, seg, texto);
            case "destino t2":
                return validateText(destinoInputT2Locator,seg,texto);
            default:
                return false;
        }
    }
    @Override
    public void deleteCookies() {
        super.deleteCookies();
        WebElement e = findWebElement(entendiBtnLocator, 3);
        if(e != null) {
            click(e);
        }
    }

    public void selectOptNavBar(String option, int seg){
        String[] opciones = new String[]{
                "Alojamientos",
                "Vuelos",
                "Paquetes",
                "Ofertas",
                "Alquileres",
                "Actividades",
                "Escapadas",
                "Autos",
                "Disney",
                "Asistencias",
                "Traslados"
        };
        int i=0;
        while(!opciones[i].equalsIgnoreCase(option)){
            if(opciones[i].equalsIgnoreCase(option)){
                click(By.xpath("//ul[@class='header-list-products']//descendant::a[@title='"+opciones[i]+"']"), seg);
            } else {
                i++;
            }
        }
    }

}
