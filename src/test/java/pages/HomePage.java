package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import utilidades.ClaseBase;



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

    By fechaIDaT1Loc = By.xpath("//span[text()='Tramo 1']//following-sibling::div//descendant::input[@placeholder='Ida']");
    By fechaIDaT2Loc = By.xpath("//span[text()='Tramo 2']//following-sibling::div//descendant::input[@placeholder='Ida']");
    By fechaVueltaT1Loc = By.xpath("//span[text()='Tramo 1']//following-sibling::div//descendant::input[@placeholder='Vuelta']");
    By fechaVueltaT2Loc = By.xpath("//span[text()='Tramo 2']//following-sibling::div//descendant::input[@placeholder='Vuelta']");
    //Acciones del page

    public void go(){
        loadPage(url);
    }

    public void selectNoBenefits(int seg){
        click(noBenefitsBtn, seg);
    }

    public void selectFlightOption(String option, int seg) {
        String[] tipoVuelos = {"ida y vuelta", "Solo ida", "Multidestino", "Vuelo + Alojamiento"};

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

    public void writeAndEnterDestino(String destino, String tramo, int seg) {
        WebElement element = null;
        if(destino == "Tramo 1"){
            element = findWebElement(destinoInputT1Locator, seg);
        } else if(destino ==  "Tramo 2"){
            element = findWebElement(destinoInputT2Locator, seg);
        }
        new Actions(getDriver())
                .sendKeys(element, destino)
                .click()
                .pause(20)
                .sendKeys(Keys.ENTER)
                .pause(20)
                .perform();
    }

    public void writeAndEnterOrigen(String origen, String tramo, int seg){
        WebElement element = null;
        if(origen == "Tramo 1"){
            element = findWebElement(origenInputT1Locator, seg);
        } else if(origen ==  "Tramo 2"){
            element = findWebElement(origenInputT2Locator, seg);
        }
        new Actions(getDriver())
                .sendKeys(element, origen)
                .click()
                .pause(20)
                .sendKeys(Keys.ENTER)
                .pause(20)
                .perform();
    }
    public void selectFecha(String tramo, String tipoFecha,String dia, String año, String mes) throws InterruptedException {
        String añoMes = año.concat("-").concat(mes);
        int mesNum = Integer.parseInt(mes);
        switch (tipoFecha) {
            case "Ida":
                if (tramo == "Tramo 1") {
                    click(fechaIDaT1Loc, 0);
                } else if (tramo == "Tramo 2") {
                    click(fechaIDaT2Loc, 0);
                }
                break;
            case "Vuelta":
                if (tramo == "Tramo 1") {
                    click(fechaVueltaT1Loc, 0);
                } else if (tramo == "Tramo 2") {
                    click(fechaVueltaT2Loc, 0);
                }
                break;
        }


        if (mesNum > 07) {
            WebElement arrow = findWebElements(By.xpath("//a[@class='calendar-arrow-right']"), 0).get(0);
            for (int i = 0; i < (mesNum-7) ; i++) {
                arrow.click();
                abruptWaitFor(2000);
            }
        }
        By dateLoc = By.xpath("//div[@data-month='"+añoMes+"']//descendant::div[@class='sbox5-monthgrid-datenumber-number' and text()='"+dia+"']//parent::div[starts-with(@class, 'sbox5-monthgrid-datenumber')]");
        findWebElements(dateLoc, 0).get(0).click();
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
        boolean success = false;
        while(!success && i < opciones.length){
            if(opciones[i].equalsIgnoreCase(option)){
                click(By.xpath("//a[@title='"+opciones[i]+"']"), seg);
                success=true;
            }
                i++;

        }
    }

}
