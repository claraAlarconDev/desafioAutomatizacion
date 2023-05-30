package tests;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.OfertasPage;
import pages.PaquetesPage;
import utilidades.DataDriven;
import utilidades.PropertiesDriven;

import java.io.IOException;
import java.util.List;

public class Tests {

    //Atributos
    private WebDriver driver;
    private HomePage home;
    private OfertasPage ofertas;
    private PaquetesPage paquetes;

    private DataDriven data;

    private List<String> datos;

    @BeforeSuite
    public void inicioSuiteDePruebas(){
        System.out.println("Inicio de suite de pruebas automatizadas");
    }

    @BeforeClass
    public void preparacionClase(){
        PropertiesDriven properties = new PropertiesDriven();

        data = new DataDriven();

        home = new HomePage(this.driver);
        home.connectDriver(properties.obtenerProperties("rutaDriver"), properties.obtenerProperties("browserProperty"), properties.obtenerProperties("browser"));
        ofertas = new OfertasPage(home.getDriver());
        paquetes = new PaquetesPage(home.getDriver());
    }

    @BeforeMethod
    public void preparacionTests(){
        home.go();
        home.maximizeWindow();
    }


    @Test
    public void TC001_pruebaCompletarForm() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC001");
        home.selectNoBenefits(0);
        home.deleteCookies();
        home.selectFlightOption(datos.get(1), 0);
        home.writeOrigen(datos.get(5), datos.get(7),0);
        home.writeDestino(datos.get(6), datos.get(7), 0);

    }

    @Test
    public void TC002_validarTextosAlertDelForm() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC002");
        //home.selectNoBenefits(0);
        //home.deleteCookies();
        //home.refreshPage();
        home.selectFlightOption(datos.get(1), 0);
        home.writeOrigen(datos.get(5), datos.get(7),0);
        home.click(By.xpath(datos.get(10)), 0);
        Assert.assertEquals("Ingresa un destino", home.findWebElement(By.xpath(datos.get(9)), 0).getText());
        Assert.assertEquals("Ingresa una fecha de partida", home.findWebElement(By.xpath(datos.get(11)), 6).getText());
    }

    @Test
    public void TC003_buscarPaquetesForma1() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC003");
        home.selectOptNavBar(datos.get(1), 0);

    }



    @AfterMethod

    public void cerrarBrowser(){

    }

}
