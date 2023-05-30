package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilidades.ClaseBase;

import java.util.List;

public class OfertasPage extends ClaseBase {

    //Constructor
    public OfertasPage(WebDriver driver){
        super(driver);
    }

    //Identificar localizadores
    //By menuTagFilter = By.xpath("//div[@class='filter-tags-wrapper']//descendant::span[normalize-space()='Mes' and @class='tag-text']");
    //By destinoTagFilter = By.xpath("//div[@class='filter-tags-wrapper']//descendant::span[normalize-space()='Destino' and @class='tag-text']");
    //By checkboxFilter = By.xpath("//eva-checkbox//parent::div[@class='offers-filters-content']");
    By mesFilter = By.xpath("//span[contains(text(), 'Mes')]//parent::span[@class='eva-3-tag']");
    By destinoFilter = By.xpath("//span[contains(text(), 'Destino')]//parent::span[@class='eva-3-tag']");
    //Otros atributos
    //List<WebElement> checkBoxFilters = findWebElements(By.xpath("//eva-checkbox//parent::div[@class='offers-filters-content']"), 0);
    //WebElement checkBoxesMes = checkBoxFilters.get(0);
    //WebElement checkBoxesDestino = checkBoxFilters.get(1);
    //Acciones del page


    public void selectMes(String mes){
        findWebElement(mesFilter, 6).click();
        By locator = By.xpath("//em[contains(text(), '"+mes+"')]//parent::span");
        findWebElement(locator, 0).click();
    }

    public void selectDestino(String destino){
        findWebElement(destinoFilter,6);
        By locator = By.xpath("//em[contains(text(), '"+destino+"')]//parent::span");
    }
}
