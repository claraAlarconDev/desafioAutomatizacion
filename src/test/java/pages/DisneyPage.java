package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilidades.ClaseBase;

import java.util.List;

public class DisneyPage extends ClaseBase {

    //Atributos
    By elegirEntrada1Loc =  By.xpath("//em[text()='Elegir Entrada']//parent::span[@class='eva-3-btn -primary -md -eva-3-fwidth']");
    By elegirEntrada2Loc = By.xpath("//em[text()='Elegir Entrada']//parent::button");
    By mesLoc = By.xpath("//div[@class='sbox5-monthgrid-title-month']");
    By yearLoc = By.xpath("//div[@class='sbox5-monthgrid-title-year']");
    By adultosPlus = By.xpath("//span[text()='Adultos']//parent::div//following-sibling::div//child::button[@class='steppers-icon-right eva-3-icon-plus']");
    By adultosMinus = By.xpath("//span[text()='Adultos']//parent::div//following-sibling::div//child::button[starts-with(@class, 'steppers-icon-left eva-3-icon-minus')]");
    By menoresPlus = By.xpath("//span[text()='Menores']//parent::div//following-sibling::div//child::button[@class='steppers-icon-right eva-3-icon-plus']");
    By menoresMinus = By.xpath("//span[text()='Menores']//parent::div//following-sibling::div//child::button[starts-with(@class, 'steppers-icon-left eva-3-icon-minus')]");
    By comprarBtn = By.xpath("//span[@class='button']//descendant::em[text()='Comprar']");
    By calendarArrowRigth = By.xpath("class='calendar-arrow-right'");
    //By fechaLoc = By.xpath("//div[@class='sbox5-monthgrid-datenumber-number' and text()='6']//parent::div[starts-with(@class, 'sbox5-monthgrid-datenumber ')]")
    public DisneyPage(WebDriver driver){
        super(driver);
    }

    public void pressElegirEntrada(int numBtn, int seg){
        if(numBtn == 1){
            click(elegirEntrada1Loc, seg);
        } else if(numBtn == 2){
            click(elegirEntrada2Loc, seg);
        }
    }

    public void seleccionarFecha(int mes, String dia){
        int mesActual = 6;
        if(mes > mesActual){
            for (int i = 0; i < (mesActual - mes); i++) {
                click(calendarArrowRigth, 0);
            }
        }
        By fecha = By.xpath("//div[@class='sbox5-monthgrid-datenumber-number' and text()='"+dia+"']//parent::div[starts-with(@class, 'sbox5-monthgrid-datenumber')]");
        click(fecha, 0);
    }

    public void addAdulto(int cant, int seg){
        for (int i = 0; i < cant; i++) {
            click(adultosPlus, seg);
        }
    }

    public void restarAdultos(int cant, int seg){
        for (int i = 0; i < cant; i++) {
            click(adultosMinus, seg);
        }
    }

    public void addMenores(int cant, int seg){
        for (int i = 0; i < cant; i++) {
            click(menoresPlus, seg);
        }
    }

    public void restarMenores(int cant, int seg){
        for (int i = 0; i < cant; i++) {
            click(menoresMinus, seg);
        }
    }

    public void addAdicional(int i, int seg){
        List<WebElement> lista = findWebElements(By.xpath("//a//child::em[text()='Agregar este adicional']"), seg);
        click(lista.get(i));
    }

    public void quitarAdicional(int i, int seg){
        List<WebElement> lista = findWebElements(By.xpath("//span[@class='remove-button']"), 0);
        click(lista.get(i));
    }
    public void comprar(){
        click(comprarBtn, 0);
    }
}
