package AdrianaAlvarez;
// Del Caso de Prueba 55
// Verificar que un usuario paciente puede ver la información de sus propias tomas de glucosa, pudiendo ver el detalle de la toma que generó un episodio de hipoglucemia
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerRegistrosConAlertaTest {

    private WebDriver driver;

    // ------------------------------------------
    // @BeforeTest — Abre Chrome antes del @Test
    // ------------------------------------------
    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // ------------------------------------------
    // @Test 
    // ------------------------------------------
    @Test
    public void deberiaMostrarRegistroConAlertaDeHipoglucemia() {

        // ------------------------------------------
        // PREPARACIÓN
        // ------------------------------------------

        // PASO 1: Abrimos en localhost
        driver.get("http://localhost:4200");
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        // PASO 2: Escribir correo del login
        WebElement inputEmail = driver.findElement(By.xpath(
            "/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"
        ));
        inputEmail.sendKeys("camialv2004@gmail.com");

        // PASO 3: Escribir contraseña del login
        WebElement inputPassword = driver.findElement(By.xpath(
            "/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"
        ));
        inputPassword.sendKeys("123456");

        // PASO 4: Clic en Ingresar del login
        WebElement btnLogin = driver.findElement(By.xpath(
            "/html/body/app-root/app-login/section/div[2]/div/form/button"
        ));
        btnLogin.click();

        // Esperamos que Angular procese el token y cargue el dashboard del paciente
        try { TimeUnit.SECONDS.sleep(8); } catch (InterruptedException e) { e.printStackTrace(); }

        // ------------------------------------------
        // EJECUCIÓN — LÓGICA DE LA PRUEBA
        // ------------------------------------------

        // PASO 5: Navegar a la pantalla de Mis Registros
        driver.get("http://localhost:4200/paciente/registros");
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        // ------------------------------------------
        // VERIFICACIÓN
        // ------------------------------------------

        WebDriverWait wait = new WebDriverWait(driver, 20);

        // ASSERT 1: El título "Mis registros" es visible
        WebElement tituloPagina = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Mis registros')]")
            )
        );
        Assert.assertTrue(
            "La pantalla Mis Registros no cargó correctamente",
            tituloPagina.isDisplayed()
        );

        // Esperamos a que aparezca al menos una card marcada con la clase 'alerta'
        WebElement cardConAlerta = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'card-glucosa') and contains(@class,'alerta')]")
            )
        );

        // ASSERT 2: La card con alerta es visible en la lista de registros
        Assert.assertTrue(
            "No se encontró ningún registro con alerta en la lista — debería existir al menos uno de hipoglucemia",
            cardConAlerta.isDisplayed()
        );

        // PASO 6: Clic en el botón "Detalle" de la primera card con alerta
        WebElement btnDetalle = cardConAlerta.findElement(
            By.xpath(".//button[contains(@class,'btn-detalle')]")
        );
        btnDetalle.click();
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        // ASSERT 3: El modal de detalle está visible
        WebElement modalDetalle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'modal-content')]")
            )
        );
        Assert.assertTrue(
            "El modal de detalle del registro no se abrió",
            modalDetalle.isDisplayed()
        );
    }

    // ------------------------------------------
    // @AfterTest — Cierra Chrome al terminar
    // ------------------------------------------
    @AfterTest
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}
