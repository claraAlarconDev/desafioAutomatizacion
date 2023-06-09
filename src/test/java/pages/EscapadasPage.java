package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

public class EscapadasPage extends ClaseBase {

    By fechaFilter = By.xpath("//a[@id='date-filter']");
    By personasFilter = By.xpath("//a[@id='passengers-filter']");
    By distanciaFilter = By.xpath("//a[@id='distance-filter']");
    By btnsAniadirHabitacion = By.xpath("//em[starts-with(text(),'Añadir habi')]//parent::button[contains(@class,'add-room-button')]");
    By aplicarHabBtns = By.xpath("//em[text()='Aplicar']//parent::a");
    By aplicarDistanciaBtns = By.xpath("//em[text()=' Aplicar ']//parent::a");
    By escapadasDetalleBtns = By.xpath("//em[text()='Ver detalle']//parent::a");
    public EscapadasPage(WebDriver driver){
        super(driver);
    }

    public void selectDistancia(String horas){
        click(distanciaFilter, 0);
        click(By.xpath("//span[@class='eva-3-radio']//descendant::label[starts-with(text(), ' Hasta "+horas+" ')]"), 0);
        click(findWebElements(aplicarDistanciaBtns,0).get(0));
    }
    public void aniadirHabitacion(int i){
        click(personasFilter,0);
        for (int j = 0; j < i; j++) {
            click(findWebElements(btnsAniadirHabitacion,0).get(0));
        }
        click(aplicarHabBtns, 0);
    }

    public void selectEscapada(int i){
        click(findWebElements(escapadasDetalleBtns, 0).get(i));
    }
}
