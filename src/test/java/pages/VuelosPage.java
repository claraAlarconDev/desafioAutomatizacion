package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilidades.ClaseBase;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VuelosPage extends ClaseBase {

    By siguienteListBtnLocator = By.xpath("//div[@class='eva-3-cluster-basic cluster-clickable -eva-3-shadow-line-hover -border-none']//div[contains(span, 'Siguiente')]");
    By filterArrowForward = By.xpath("//span[@class='matrix-arrow matrix-forward']");
    By filterArrowBackward = By.xpath("//span[@class='matrix-arrow matrix-backward']");
    By addAdultoLoc = By.xpath("//p[text()='Adultos']//parent::span[@class='passenger-description']//following-sibling::span//descendant::a[@class='steppers-icon-right eva-3-icon-plus']");

    By restarAdultos = By.xpath("//p[text()='Adultos']//parent::span[@class='passenger-description']//following-sibling::span//descendant::a[@class='steppers-icon-left eva-3-icon-minus']");
    By addMenores = By.xpath("//p[text()='Menores']//parent::span[@class='passenger-description']//following-sibling::span//descendant::a[@class='steppers-icon-right eva-3-icon-plus']");
    By restarMenores = By.xpath("//p[text()='Menores']//parent::span[@class='passenger-description']//following-sibling::span//descendant::a[@class='steppers-icon-left eva-3-icon-minus']");

    By noBenefitsBtn = By.xpath("//em[contains(text(), 'No quiero beneficios')]//parent::span[@class='login-aggressive--button login-aggressive--button-close shifu-3-btn-ghost']");
    By continuarBtn = By.xpath("//a//child::em[@class='btn-text' and contains(text(),'Continuar')]");
    By buyBtnLoc = By.xpath("//buy-button");

    Actions action = new Actions(getDriver());

    public VuelosPage(WebDriver driver){
        super(driver);
    }

    public void clickSiguiente(){
        List<WebElement> lista = findWebElements(siguienteListBtnLocator, 0);
        WebElement btn = lista.get(0);
        btn.click();
    }
    public void selectNoBenefits(int seg){
        click(noBenefitsBtn, seg);
    }
    public void usePopularFilters(String filtro){
        String[] lista = new String[]{
                "Vuelos directos",
                "1 Escala",
                "Vuelos con equipaje",
                "Ida a la noche",
                "Ida a la mañana"
        };
        boolean success = false;
        int i = 0;
        while(!success && i < lista.length){
            if(lista[i].equalsIgnoreCase(filtro)){
                if(filtro.equalsIgnoreCase("Ida a la mañana")){
                    click(filterArrowForward, 0);
                }
                findWebElement(By.xpath("//span[contains(text(), '"+lista[i]+"')]//parent::span[@class='eva-3-tag']"),0);
            }
            i++;
        }
    }

    public void addAdultos(int num){
        //for (int i = 0; i < num; i++) {
          //  click(addAdultoLoc, 0);
        //}
        for (int i = 0; i < num; i++) {
            action.click(findWebElement(addAdultoLoc, 0))
                    .pause(2)
                    .perform();
        }
    }
    public void restarAdultos(int num){
        for (int i = 0; i < num; i++) {
            click(restarAdultos,0);
        }
    }

    public void addMenores(int num){
        for (int i = 0; i < num; i++) {
            click(addMenores, 0);
        }
    }

    public void restarMenores(int num){
        for (int i = 0; i < num; i++) {
            click(restarMenores, 0);
        }
    }

    public void continuar(){
        findWebElement(continuarBtn, 0).click();
    }

    public void buy(int i, int seg){
        ArrayList<WebElement> buyBtns = new ArrayList<>(findWebElements(buyBtnLoc, seg));
        buyBtns.get(i).click();
    }
}
