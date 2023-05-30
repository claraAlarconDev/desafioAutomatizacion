package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilidades.ClaseBase;

import java.util.ArrayList;
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
    By loginIncentiveLoc = By.xpath("//div[@class='login-incentive--header -hotels-discount']//child::i");
    By facebookSubscribeBtnLoc = By.xpath("//i[@class='shifu-3-icon-social-facebook btn-icon']//ancestor::div[@class='login-incentive--content']//child::button[@type='button']");
    By googleSubsBtnLoc = By.xpath("//div[@class='nsm7Bb-HzV7m-LgbsSe-bN97Pc-sM5MNb oXtfBe-l4eHX']");
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

    public void closeLoginIncentiveFrame(){
        click(findWebElement(loginIncentiveLoc,0));
    }

    public void subscribeWith(String option){
        String[] opciones = new String[]{"Facebook", "Google"};

            if(opciones[0].equalsIgnoreCase(option)){
                click(facebookSubscribeBtnLoc,0);
            } else if(opciones[1].equalsIgnoreCase(option)) {
                click(googleSubsBtnLoc, 0);
            }

    }
}
