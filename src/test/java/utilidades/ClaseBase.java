package utilidades;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClaseBase {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement element;

    private JavascriptExecutor js;
    private File file;

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

    public WebDriverWait createWait(WebDriver driver, int seg){
        if(seg <= 0){
            this.wait = new WebDriverWait(getDriver(), 5);
        } else {
            this.wait = new WebDriverWait(getDriver(), seg);
        }
        return this.wait;
    }

    public WebElement findWebElement(By locator, int segundos){
       // return getDriver().findElement(locator);
        return createWait(getDriver(), segundos).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findWebElements(By locator, int segundos){
        //return getDriver().findElements(locator);
        return createWait(getDriver(), segundos).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public void takeScreenShot() throws IOException {
        file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

    }
    public void write(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
    public void write(By locator, int seg ,String text){
        element = findWebElement(locator, seg);
        element.clear();
        element.sendKeys(text);
    }


    public boolean validateText(By locator, int segundos, String texto){
        element = findWebElement(locator, (segundos/2));
        return createWait(getDriver(), (segundos/2)).until(ExpectedConditions.textToBePresentInElement(element, texto));
    };


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

    public void click(WebElement e){
        e.click();
    }
    public void click(By locator, int seg){
       findWebElement(locator, seg).click();
    }

    public boolean isSelected(By locator, int seg){
       return findWebElement(locator, seg).isSelected();
    }

    public boolean isDiplayed(By locator, int seg){
        return findWebElement(locator, seg).isDisplayed();
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
        this.driver.get(url);
    }

    public void addKeysCombination(By locator, Keys key, int seg){
        findWebElement(locator, seg).sendKeys(key);
    }

    public void abruptWaitFor(int milisegundos) throws InterruptedException{
            Thread.sleep(milisegundos);
    }


    public WebDriver connectDriver( String path, String property, String browser){
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

    public void scrollToElement(By locator){
        WebElement element = findWebElement(locator, 0);
        new Actions(getDriver())
                .moveToElement(element)
                .perform();
    }

    public void scroll(int x, int y){
       this.js = (JavascriptExecutor) getDriver();
        this.js.executeScript("window.scroll("+x+","+y+")");
        /*new Actions(getDriver())
                .moveByOffset(x,y)
                .perform();*/

    }

    public void switchToTab(int i){
        ArrayList<String> allTabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(allTabs.get(i));
    }

    public void deleteCookies(){
        getDriver().manage().deleteAllCookies();
    }

    public boolean elementExists(By locator, int seg){
        return (findWebElement(locator, seg)!=null) ? true : false;
    }
}
