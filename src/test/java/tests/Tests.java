package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utilidades.DataDriven;
import utilidades.PropertiesDriven;

import java.io.IOException;
import java.util.List;


public class Tests {

    //Atributos
    PropertiesDriven properties;
    private WebDriver driver;
    private HomePage home;
    private OfertasPage ofertas;
    private AsistenciasPage asistencias;
    private PaquetesPage paquetes;
    private FacebookLoginPage facebook;
    private VuelosPage vuelos;
    public DisneyPage disney;

    private DataDriven data;

    private List<String> datos;
    private CheckoutPage checkoutPage;

    @BeforeSuite
    public void inicioSuiteDePruebas() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println("Inicio de suite de pruebas automatizadas");
    }

    @BeforeClass
    public void preparacionClase() {
        properties = new PropertiesDriven();
        data = new DataDriven();
        /*home = new HomePage(driver);
        home.connectDriver(properties.obtenerProperties("rutaDriver"), properties.obtenerProperties("browserProperty"), properties.obtenerProperties("browser"));
        ofertas = new OfertasPage(home.getDriver());
        paquetes = new PaquetesPage(home.getDriver());
        vuelos = new VuelosPage(home.getDriver());*/
    }

    @BeforeMethod
    public void preparacionTests() {
        //PropertiesDriven properties = new PropertiesDriven();
        //data = new DataDriven();
        home = new HomePage(driver);
        home.connectDriver(properties.obtenerProperties("rutaDriver"), properties.obtenerProperties("browserProperty"), properties.obtenerProperties("browser"));
        ofertas = new OfertasPage(home.getDriver());
        paquetes = new PaquetesPage(home.getDriver());
        vuelos = new VuelosPage(home.getDriver());
        facebook = new FacebookLoginPage(home.getDriver());
        disney = new DisneyPage(home.getDriver());
        asistencias = new AsistenciasPage(home.getDriver());
        checkoutPage = new CheckoutPage(home.getDriver());
        home.go();
        home.maximizeWindow();
        home.selectNoBenefits(0);
        home.deleteCookies();
    }


    @Test
    public void TC001_pruebaCompletarForm1() throws Exception {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC001");
        home.selectFlightOption(datos.get(1), 0);
        //home.writeOrigen(datos.get(5), datos.get(7),0);
        //home.writeDestino(datos.get(6), datos.get(7), 0);
        home.writeAndEnterOrigen(datos.get(2), datos.get(4), 0);
        home.writeAndEnterDestino(datos.get(3), datos.get(4), 0);
        home.scroll(0, 200);
        home.selectFecha(datos.get(4), datos.get(5), datos.get(6), datos.get(7), datos.get(8));
    }

    @Test
    public void TC002_validarTextosAlertDelForm() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC002");
        home.selectFlightOption(datos.get(1), 0);
        home.writeOrigen(datos.get(5), datos.get(7), 0);
        home.click(By.xpath(datos.get(10)), 0);
        Assert.assertEquals(datos.get(12), home.findWebElement(By.xpath(datos.get(9)), 0).getText());
        Assert.assertEquals(datos.get(13), home.findWebElement(By.xpath(datos.get(11)), 6).getText());
    }

    @Test
    public void TC003_buscarOfertasForma1() throws Exception {
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
    public void TC004_buscarOfertasNoHayResultados() throws Exception {
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
    public void TC005_ingresarSesionFacebook() {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC005");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.selectOptNavBar(datos.get(1), 0);
        ofertas.subscribeWith(datos.get(2));
        ofertas.getDriver().switchTo().window("Facebook - Google Chrome");
        facebook.fillEmail("user@gmail.com");
        facebook.fillPass("12345");
    }

    @Test
    public void TC006_ingresarSesionGoogle() {

    }

    @Test
    public void TC007_eligeTuEntradaDisney() throws InterruptedException {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC007");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.selectOptNavBar(datos.get(1), 0);
        disney.pressElegirEntrada(Integer.parseInt(datos.get(2)), 0);
        disney.seleccionarFecha(Integer.parseInt(datos.get(5)), datos.get(6));
        disney.addAdulto(Integer.parseInt(datos.get(3)), 0);
        disney.addMenores(Integer.parseInt(datos.get(2)), 0);
        disney.comprar();
        disney.abruptWaitFor(5000);
        System.out.println(disney.getDriver().getTitle());
        Assert.assertEquals(datos.get(4), disney.getDriver().getTitle());

    }

    @Test
    public void TC008_pruebaCompletarForm2() throws Exception {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC008");
        home.selectFlightOption(datos.get(1), 0);
        //home.writeOrigen(datos.get(5), datos.get(7),0);
        //home.writeDestino(datos.get(6), datos.get(7), 0);
        home.writeAndEnterOrigen(datos.get(2), datos.get(3), 0);
        home.writeAndEnterDestino(datos.get(4), datos.get(3), 0);
        home.scroll(0, 200);
        home.selectFecha(datos.get(3), datos.get(5), datos.get(6), datos.get(7), datos.get(8));
        home.writeAndEnterOrigen(datos.get(4), datos.get(14), 0);
        home.writeAndEnterDestino(datos.get(9), datos.get(14), 0);
        home.selectFecha(datos.get(4), datos.get(10), datos.get(11), datos.get(12), datos.get(13));
    }

    @Test
    public void TC009_ComprarAsistencia() {
        home.selectOptNavBar("Asistencias", 0);
        asistencias.selectDestino("Argentina",0);
        asistencias.selectFecha("Partida","06", "2023", "18");
        //asistencias.aplicarFechastBtn();
        try {
            asistencias.abruptWaitFor(4000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        asistencias.selectFecha("Regreso","06", "2023", "24");
        asistencias.aplicarFechastBtn();
        asistencias.buscar();
        asistencias.changeCurrency("USD");
        asistencias.comprarBts(0);
        try {
            asistencias.abruptWaitFor(4000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        System.out.println(checkoutPage.getDriver().getTitle());
        Assert.assertEquals("Despegar - Checkout", checkoutPage.getDriver().getTitle());

    }

    @Test
    public void TC010() {

    }

    @Test
    public void TC011() {

    }

    @Test
    public void TC012() {

    }



    @AfterMethod

    public void cerrarBrowser(){
       // home.getDriver().close();
    }

}
