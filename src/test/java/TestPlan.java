import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Login exitoso")
    public static void loginExitoso(){

        }
    @Test(testName = "Agregar item al carrito")
    public static void verificarItemAgregado(){
        }

    @Test(testName = "Credenciales incorrectas - Verificar login no válido (TC 1)")
    public static void verificarLoginNoValido(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarConDatosIncorrectos();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(loginForm.getMensajeDeIngresoInvalido(), "Epic sadface: Username and password do not match any user in this service");
       }

    @Test(testName = "Desloguearse correctamente (TC 2)")
    public static void desloguearseCorrectamente(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarConDatosCorrectos();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.pressBurgerMenu();
        productsPage.pressLogout();

        Assert.assertEquals(loginForm.getLoginClickText(), "Login");

        }

    @Test(testName = "Verificar Carrito de compras (TC 3)")
    public static void verificarCarro(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarConDatosCorrectos();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCartBikeLight();
        Assert.assertEquals(productsPage.getCardBadge(), "1");

    }

    @Test(testName = "Loguearse Correctamente")
    public static void loginCorrecto(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.ingresarConDatosCorrectos();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(), "PRODUCTS");
    }
    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
