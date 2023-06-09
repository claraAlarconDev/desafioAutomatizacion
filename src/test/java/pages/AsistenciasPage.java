package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

import java.util.ArrayList;
import java.util.List;

public class AsistenciasPage extends ClaseBase {

    String[] listaDestinos = {"Europa", "Estados Unidos", "Argentina", "Latinoamerica","Resto del mundo"};
    By destinoLoc = By.xpath("//label[text()='Destino']//following-sibling::div//descendant::select[@class='select-tag']");
    By europaDestinoLoc = By.xpath("//option[@value='europe']");
    By argentinaLoc = By.xpath("//option[@value='national']");
    By restoDelMundoLoc = By.xpath("//option[@value='others']");
    By latamLoc= By.xpath("//option[@value='latam']");
    By usaLoc = By.xpath("//option[@value='unitedstates']");
    By btnAplicarFechas = By.xpath("//em[text()='Aplicar']//parent::button");

    By fechaPartida = By.xpath("//input[@placeholder='Partida']");
    By fechaRegreso = By.xpath("//input[@placeholder='Regreso']");
    By arrowRigth = By.xpath("//a[@class='calendar-arrow-right']");
    By btnBuscar = By.xpath("//em[text()='Buscar']//ancestor::button");
    String[] currencyOptions = {"ARS", "USD"};
    By comprarBtns = By.xpath("//em[@class='btn-text' and text()='Comprar']//parent::button");


    public AsistenciasPage(WebDriver driver){
        super(driver);
    }


    public void selectDestino(String destino, int seg){
        int i = 0;
        boolean success = false;
        while(!success  && i < listaDestinos.length){
            if(destino.equalsIgnoreCase(listaDestinos[i])){
                click(destinoLoc, 0);
                switch (listaDestinos[i]){
                    case "Europa":
                            click(europaDestinoLoc, seg);
                        break;
                    case "Latinoamerica":
                            click(latamLoc, seg);
                        break;
                    case "Estados Unidos":
                            click(usaLoc, seg);
                        break;
                    case "Argentina":
                            click(argentinaLoc, seg);
                        break;
                    case "Resto del mundo":
                            click(restoDelMundoLoc, seg);
                        break;
                }
                success=true;
            }
            i++;
        }
    }

    public void selectFecha(String tipoFecha, String mes, String año, String dia){
        int mesNum = Integer.parseInt(mes);
        String mesAño = año.concat("-").concat(mes);
        switch(tipoFecha){
            case "Partida":
                    click(fechaPartida, 0);
                break;
            case "Regreso":
                    //click(fechaRegreso, 0);
                break;
        }
        if(mesNum > 8){
            for (int i = 0; i < (mesNum - 8 -1) ; i++) {
                click(arrowRigth, 0);
            }
        }

            //click(By.xpath("//div[@data-month='" + mesAño + "']//descendant::div[text()='" + dia + "']//parent::div[starts-with(@class,'sbox5-monthgrid-datenumber ')]"), 0);
        click(By.xpath("//div[@data-month='" + mesAño + "']//descendant::div[text()='" + dia + "']"), 0);
    }

    public void aplicarFechastBtn(){
        click(btnAplicarFechas, 0);
    }
    public void buscar(){
        click(btnBuscar, 0);
    }

    public void changeCurrency(String currency){
        int i = 0;
        boolean success = false;
        while (!success && i < currencyOptions.length){
            if(currency.equalsIgnoreCase(currencyOptions[i])){
                click(By.xpath("//option[@value='"+currencyOptions[i]+"']"), 0);
                success = true;
            }
            i++;
        }
    }
    public void comprarBts(int i){
        findWebElements(comprarBtns, 0).get(i).click();
    }

}
