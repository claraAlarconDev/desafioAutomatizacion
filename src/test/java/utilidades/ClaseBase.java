package utilidades;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ClaseBase {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement element;

    //Constructor
    public ClaseBase() {
    }

    public ClaseBase(WebDriver driver) {
       setDriver(driver);
    }
    //setters
    private void setDriver(WebDriver driver) {
        this.driver=driver;
    }
    //getters
    public WebDriver getDriver() {
        return this.driver;
    }

    public WebElement findWebElement(By locator, int segundos){
       // return getDriver().findElement(locator);
        this.wait = new WebDriverWait(getDriver(), segundos);
        return this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findWebElements(By locator, int segundos){
        //return getDriver().findElements(locator);
        this.wait = new WebDriverWait(getDriver(), segundos);
        return this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void write(By locator, int seg ,String text){
        element = findWebElement(locator, seg);
        element.clear();
        element.sendKeys(text);
    }

    public boolean validateUrl(String url){
        return (url == getDriver().getCurrentUrl())? true: false;
    }

    public String getText(By locator, int seg){
        element = findWebElement(locator, seg);
        return element.getText();
    }

    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void click(By locator, int seg){
       findWebElement(locator, seg).click();
    }

    public void navigateForward(){
        getDriver().navigate().forward();
    }

    public void navigateBack(){
        getDriver().navigate().back();
    }

    public void navigateTo(String url){
        getDriver().navigate().to(url);
    }

    public void loadPage(String url){
        getDriver().get(url);
    }

    public void addKeysCombination(By locator, Keys key, int seg){
        findWebElement(locator, seg).sendKeys(key);
    }

    public void abruptWaitFor(int milisegundos) throws InterruptedException{
            Thread.sleep(milisegundos);
    }

    public WebDriver connectionToDriver( String path, String property, String browser){
        switch(browser){
            case "chrome":
                System.setProperty(property, path);
                setDriver(new ChromeDriver());
                return getDriver();
            case "firefox":
                System.setProperty(property, path);
                setDriver(new FirefoxDriver());
                return getDriver();
            case "edge":
                System.setProperty(property, path);
                setDriver(new EdgeDriver());
                return getDriver();
            default:
                return getDriver();
        }

    }

    public void maximizeWindow(){
        getDriver().manage().window().maximize();
    }

    public void irAframeByIDorName(String nameOrId){
        getDriver().switchTo().frame(nameOrId);
    }
}
