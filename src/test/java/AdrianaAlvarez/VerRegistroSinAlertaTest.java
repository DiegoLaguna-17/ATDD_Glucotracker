package AdrianaAlvarez;

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

public class VerRegistroSinAlertaTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void deberiaMostrarModalDetalleDeRegistroSinAlerta() {

        // ------------------------------------------
        // PREPARACIÓN 
        // ------------------------------------------

        // PASO 1: Abrimos el localhost
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

        try { TimeUnit.SECONDS.sleep(8); } catch (InterruptedException e) { e.printStackTrace(); }

        // ------------------------------------------
        // EJECUCIÓN — LÓGICA DE LA PRUEBA
        // ------------------------------------------

        // PASO 5: Ir directo a la pantalla de registros
        driver.get("http://localhost:4200/paciente/registros");
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        WebDriverWait wait = new WebDriverWait(driver, 20);

        // ---------------------------------------------------
        // VERIFICACIÓN 1 — La pantalla cargó correctamente
        // ---------------------------------------------------

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

        // ASSERT 2: Existe al menos una card sin la clase 'alerta'
        WebElement cardSinAlerta = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'card-glucosa') and not(contains(@class,'alerta'))]")
            )
        );
        Assert.assertTrue(
            "No se encontró ningún registro sin alerta en la lista",
            cardSinAlerta.isDisplayed()
        );

        // PASO 6: Clic en "Detalle" de la primera card sin alerta
        WebElement btnDetalle = cardSinAlerta.findElement(
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
            "El modal de detalle del registro sin alerta no se abrió",
            modalDetalle.isDisplayed()
        );
    }

    @AfterTest
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}
