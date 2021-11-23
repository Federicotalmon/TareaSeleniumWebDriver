import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends PageObject {

    private final String USUARIO_INCORRECTO = "user_standard";
    private final String USUARIO_CORRECTO = "standard_user";
    private final String CONTRASENIA_INCORRECTA = "sauce_secret";
    private final String CONTRASENIA_CORRECTA = "secret_sauce";

    @FindBy(id = "user-name")
    private WebElement usuario;

    @FindBy(id = "password")
    private WebElement contrasenia;

    @FindBy(id = "login-button")
    private WebElement boton_login;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement texto_error;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void ingresarConDatosCorrectos(){
        this.usuario.sendKeys(USUARIO_CORRECTO);
        this.contrasenia.sendKeys(CONTRASENIA_CORRECTA);
        this.boton_login.click();

    }

    public void ingresarConDatosIncorrectos(){
        this.usuario.sendKeys(USUARIO_INCORRECTO);
        this.contrasenia.sendKeys(CONTRASENIA_INCORRECTA);
        this.boton_login.click();

    }

    public String getMensajeDeIngresoInvalido(){
        return this.texto_error.getText();
    }

    public String getLoginClickText(){
        return this.boton_login.getAttribute("value");
    }
}
