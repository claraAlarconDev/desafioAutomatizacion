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
    private FacebookLoginPage facebook;
    private VuelosPage vuelos;
    public DisneyPage disney;

    private DataDriven data;

    private List<String> datos;
    private CheckoutPage checkoutPage;
    private EscapadasPage escapadasPage;
    private ArrepentimientoPage arrepentimientoPage;

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

    //Por problemas que me surgieron y no me dejaban ejecutar el command test, paso todas las instancias al before method
    @BeforeMethod
    public void preparacionTests() {
        home = new HomePage(driver);
        home.connectDriver(properties.obtenerProperties("rutaDriver"), properties.obtenerProperties("browserProperty"), properties.obtenerProperties("browser"));
        ofertas = new OfertasPage(home.getDriver());
        vuelos = new VuelosPage(home.getDriver());
        facebook = new FacebookLoginPage(home.getDriver());
        disney = new DisneyPage(home.getDriver());
        asistencias = new AsistenciasPage(home.getDriver());
        checkoutPage = new CheckoutPage(home.getDriver());
        escapadasPage = new EscapadasPage(home.getDriver());
        arrepentimientoPage = new ArrepentimientoPage(home.getDriver());
        home.go();
        home.maximizeWindow();
        home.selectNoBenefits(0);
        home.deleteCookies();
    }


    @Test
    public void TC001_pruebaCompletarForm1() throws Exception {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC001");
        home.selectFlightOption(datos.get(1), 10);
        //home.writeOrigen(datos.get(5), datos.get(7),10);
        //home.writeDestino(datos.get(6), datos.get(7), 10);
        home.writeAndEnterOrigen(datos.get(2), datos.get(4), 10);
        home.writeAndEnterDestino(datos.get(3), datos.get(4), 10);
        home.scroll(0, 200);
        home.selectFecha(datos.get(4), datos.get(5), datos.get(6), datos.get(7), datos.get(8));
        home.buscarBtn(10);
        Assert.assertEquals(vuelos.findWebElement(By.xpath(datos.get(9)),0).getText(), datos.get(10));
    }

    @Test
    public void TC002_validarTextosAlertDelForm() throws IOException {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC002");
        home.selectFlightOption(datos.get(1), 10);
        home.writeOrigen(datos.get(5), datos.get(7), 10);
        home.click(By.xpath(datos.get(10)), 0);
        Assert.assertEquals(home.findWebElement(By.xpath(datos.get(9)), 20).getText(), datos.get(12));
        Assert.assertEquals(home.findWebElement(By.xpath(datos.get(11)), 20).getText(), datos.get(13));
    }

    @Test
    public void TC003_buscarOfertasForma1() throws Exception {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC003");
        home.selectOptNavBar(datos.get(1), 10);
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
        //vuelos.abruptWaitFor(7000);
        vuelos.scroll(0, 500);
        //vuelos.selectNoBenefits(20);
        vuelos.buy(Integer.parseInt(datos.get(7)), 20);
        //vuelos.abruptWaitFor(7000);
        //vuelos.sumaEquipajeContinuar(20);
        Assert.assertEquals(vuelos.getDriver().getTitle(), datos.get(8));

    }

    @Test
    public void TC004_buscarOfertasNoHayResultados() throws Exception {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC004");
        home.selectOptNavBar(datos.get(1), 10);
        ofertas.closeLoginIncentiveFrame();
        ofertas.selectMes(datos.get(2));
        ofertas.abruptWaitFor(5000);
        ofertas.selectDestino(datos.get(4));
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
        home.selectOptNavBar(datos.get(1), 10);
        ofertas.subscribeWith(datos.get(2));
        ofertas.getDriver().switchTo().window(datos.get(3));
        facebook.fillEmail(datos.get(4));
        facebook.fillPass(datos.get(5));
        Assert.assertEquals(facebook.getDriver().getCurrentUrl(), datos.get(3));
    }

    @Test
    public void TC006_ComprarAsistencia() {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC006");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.selectOptNavBar(datos.get(1), 10);
        asistencias.selectDestino(datos.get(2),10);
        asistencias.selectFecha(datos.get(3),datos.get(4), datos.get(5), datos.get(6));
        //asistencias.aplicarFechastBtn();
        try {
            asistencias.abruptWaitFor(4000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        asistencias.selectFecha(datos.get(7),datos.get(4), datos.get(5), datos.get(8));
        asistencias.aplicarFechastBtn();
        asistencias.buscar();
        asistencias.changeCurrency(datos.get(9));
        asistencias.comprarBts(0);
        try {
            asistencias.abruptWaitFor(4000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        Assert.assertEquals(checkoutPage.getDriver().getTitle(), datos.get(10));

    }

    @Test
    public void TC007_eligeTuEntradaDisney() throws InterruptedException {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC007");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.selectOptNavBar(datos.get(1), 10);
        disney.pressElegirEntrada(Integer.parseInt(datos.get(2)), 10);
        disney.seleccionarFecha(Integer.parseInt(datos.get(5)), datos.get(6));
        disney.addAdulto(Integer.parseInt(datos.get(3)), 10);
        disney.addMenores(Integer.parseInt(datos.get(2)), 10);
        disney.comprar();
        disney.abruptWaitFor(5000);
        Assert.assertEquals(disney.getDriver().getTitle(), datos.get(4));

    }

    @Test
    public void TC008_pruebaCompletarForm2() throws Exception {
        datos = data.obtenerDatosDePrueba("DatosTC", "TC008");
        home.selectFlightOption(datos.get(1), 10);
        home.writeAndEnterOrigen(datos.get(2), datos.get(3), 10);
        home.writeAndEnterDestino(datos.get(4), datos.get(3), 10);
        home.scroll(0, 200);
        home.selectFecha(datos.get(3), datos.get(5), datos.get(6), datos.get(7), datos.get(8));
        home.writeAndEnterOrigen(datos.get(4), datos.get(14), 10);
        home.writeAndEnterDestino(datos.get(9), datos.get(14), 10);
        home.selectFecha(datos.get(4), datos.get(10), datos.get(11), datos.get(12), datos.get(13));
        //Assert
        Assert.assertEquals(vuelos.findWebElement(By.xpath(datos.get(15)),0).getText(), datos.get(16));
    }

    @Test
    public void TC009_ComprarAsistenciaYCheckout() {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC009");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.selectOptNavBar(datos.get(1), 10);
        asistencias.selectDestino(datos.get(2),10);
        asistencias.selectFecha(datos.get(3),datos.get(4), datos.get(5), datos.get(6));
        //asistencias.aplicarFechastBtn();
        try {
            asistencias.abruptWaitFor(4000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        asistencias.selectFecha(datos.get(7),datos.get(4), datos.get(5), datos.get(8));
        asistencias.aplicarFechastBtn();
        asistencias.buscar();
        asistencias.comprarBts(0);
        try {
            asistencias.abruptWaitFor(6000);
        }catch(InterruptedException err){
            System.out.println(err);
        }

        checkoutPage.writeForm(datos.get(9), datos.get(10));
        checkoutPage.writeForm(datos.get(11), datos.get(12));
        checkoutPage.writeForm(datos.get(13), datos.get(14));
        checkoutPage.writeForm(datos.get(15), datos.get(16));
        checkoutPage.writeForm(datos.get(17), datos.get(18));
        checkoutPage.writeForm(datos.get(19), datos.get(20));
        checkoutPage.selectMedioPago(datos.get(21));
        checkoutPage.datosTarjeta(datos.get(22), datos.get(23), datos.get(24), datos.get(25), datos.get(26), datos.get(27), 10);
        Assert.assertTrue(checkoutPage.estoyEnCheckout());

    }

    @Test
    public void TC010_elegirEscapada() {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC010");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.selectOptNavBar(datos.get(1), 10);
        escapadasPage.aniadirHabitacion(Integer.parseInt(datos.get(6)));
        try {
            escapadasPage.abruptWaitFor(8000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        escapadasPage.selectDistancia(datos.get(2));
        try {
            escapadasPage.abruptWaitFor(8000);
        }catch(InterruptedException err){
            System.out.println(err);
        }
        System.out.println(escapadasPage.getDriver().getTitle());
        escapadasPage.selectEscapada(Integer.parseInt(datos.get(3)));
        escapadasPage.switchToTab(Integer.parseInt(datos.get(4)));
        Assert.assertTrue(escapadasPage.elementExists(By.xpath(datos.get(5)), 20));
    }

    @Test
    public void TC011_botonDeArrepentimientoExito() {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC011");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
        home.scroll(0, 700);
        home.meArrepenti(10);
        home.switchToTab(1);
        arrepentimientoPage.fillEmail(datos.get(3), 10);
        arrepentimientoPage.fillNumReserva(10,datos.get(4));
        arrepentimientoPage.pressContinuar(10);
        Assert.assertTrue(arrepentimientoPage.success(20));
    }

    @Test
    public void TC012_botonArrepentimientoAlert() {
        try {
            datos = data.obtenerDatosDePrueba("DatosTC", "TC011");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    home.meArrepenti(10);
    home.switchToTab(Integer.parseInt(datos.get(1)));
    arrepentimientoPage.pressContinuar(10);
    Assert.assertTrue(arrepentimientoPage.noEmail(10));
    Assert.assertTrue(arrepentimientoPage.noReserva(10));

    }



    @AfterMethod

    public void cerrarBrowser(){
        //El home.getDriver.close() en la mayoria de los casos no sirve porque unicamente cierra la ventana en la que esta el driver
       //home.getDriver().close();
       home.getDriver().quit();

    }

}
