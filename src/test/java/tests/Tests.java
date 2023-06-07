package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.OfertasPage;
import pages.PaquetesPage;
import pages.VuelosPage;
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
    private VuelosPage vuelos;

    private DataDriven data;

    private List<String> datos;

    @BeforeSuite
    public void inicioSuiteDePruebas(){
        System.out.println(System.getProperty("user.dir"));
        System.out.println("Inicio de suite de pruebas automatizadas");
    }

    @BeforeClass
    public void preparacionClase(){
        PropertiesDriven properties = new PropertiesDriven();
        data = new DataDriven();
        home = new HomePage(driver);
        home.connectDriver(properties.obtenerProperties("rutaDriver"), properties.obtenerProperties("browserProperty"), properties.obtenerProperties("browser"));
        ofertas = new OfertasPage(home.getDriver());
        paquetes = new PaquetesPage(home.getDriver());
        vuelos = new VuelosPage(home.getDriver());
    }

    @BeforeMethod
    public void preparacionTests(){
        home.go();
        home.maximizeWindow();
        home.selectNoBenefits(0);
        home.deleteCookies();
    }


    @Test
    public void TC001_pruebaCompletarForm() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC001");
        home.selectFlightOption(datos.get(1), 0);
        home.writeOrigen(datos.get(5), datos.get(7),0);
        home.writeDestino(datos.get(6), datos.get(7), 0);
        home.scroll(0,200);
        home.selectFecha("Tramo 1","Ida","24", "2023", "08");
        //home.selectFecha("Tramo 1", "Vuelta", "28", "2023", "08");
    }

    @Test
    public void TC002_validarTextosAlertDelForm() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC002");
        home.selectFlightOption(datos.get(1), 0);
        home.writeOrigen(datos.get(5), datos.get(7),0);
        home.click(By.xpath(datos.get(10)), 0);
        Assert.assertEquals("Ingresa un destino", home.findWebElement(By.xpath(datos.get(9)), 0).getText());
        Assert.assertEquals("Ingresa una fecha de partida", home.findWebElement(By.xpath(datos.get(11)), 6).getText());
    }

    @Test
    public void TC003_buscarOfertasForma1() throws IOException, InterruptedException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC003");
        home.selectOptNavBar(datos.get(1), 0);
        ofertas.closeLoginIncentiveFrame();
        ofertas.selectMes(datos.get(2));
        ofertas.abruptWaitFor(5000);
        ofertas.selectDestino(datos.get(3));
        ofertas.abruptWaitFor(5000);
        ofertas.selectFlightOffer(datos.get(4));
        vuelos.switchToTab(Integer.parseInt(datos.get(5)));
        vuelos.scroll(0, 500);
        vuelos.clickSiguiente();
        vuelos.addAdultos(Integer.parseInt(datos.get(6)));
        vuelos.restarAdultos(Integer.parseInt(datos.get(7)));
        vuelos.continuar();
        vuelos.abruptWaitFor(6000);
        vuelos.scroll(0, 500);
        vuelos.abruptWaitFor(5000);
        Assert.assertEquals(datos.get(8), vuelos.getDriver().getTitle());
    }

    @Test
    public void TC004_buscarOfertasNoHayResultados() throws IOException, InterruptedException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC004");
        home.selectOptNavBar(datos.get(1), 0);
        ofertas.closeLoginIncentiveFrame();
        ofertas.selectMes(datos.get(2));
        ofertas.abruptWaitFor(5000);
        ofertas.selectDestino(datos.get(3));
        ofertas.abruptWaitFor(5000);
        Assert.assertTrue(ofertas.notFoundFlight(5));
    }

    @Test
    public void TC005_ingresarSesionFacebook() throws InterruptedException {
        home.selectFlightOption("Solo ida", 0);
       home.writeDestino2();
    }

    @Test
    public void  TC006_ingresarSesionGoogle(){

    }

    @Test
    public void TC007_eligeTuEntradaDisney(){

    }

    @Test
    public void TC008_(){

    }

    @AfterMethod

    public void cerrarBrowser(){

    }

}
