package AdrianaAlvarez;
// Del Caso de prueba 23
// Verificar que enseña la alerta de episodio de hipoglucemia basado en los datos de glucosa registrados < 70 mg/dL si estos son registrados por el paciente de manera correcta
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

public class HipoglucemiaAlertaTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void deberiaGenerarAlertaHipoglucemia() {

        // ------------------------------------------
        // PREPARACIÓN 
        // ------------------------------------------

        driver.get("http://localhost:4200");

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        // PASO 1: Escribir correo en el login
        WebElement inputEmail = driver.findElement(By.xpath(
            "/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"
        ));
        inputEmail.sendKeys("camialv2004@gmail.com");

        // PASO 2: Escribir contraseña en el login
        WebElement inputPassword = driver.findElement(By.xpath(
            "/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"
        ));
        inputPassword.sendKeys("123456");

        // PASO 3: Clic en Ingresar en el login 
        WebElement btnLogin = driver.findElement(By.xpath(
            "/html/body/app-root/app-login/section/div[2]/div/form/button"
        ));
        btnLogin.click();

        // Esperamos que Angular procese el token y navegue a /paciente/registrar
        try { TimeUnit.SECONDS.sleep(8); } catch (InterruptedException e) { e.printStackTrace(); }

        // ------------------------------------------------------------------------------
        // EJECUCIÓN — LÓGICA DE LA PRUEBA Registrar glucosa menor a 70 (hipoglucemia)
        // ------------------------------------------------------------------------------

        // PASO 4: Ingresar nivel de glucosa = 50 
        WebElement inputGlucosa = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[1]/div[1]/input"
        ));
        inputGlucosa.sendKeys("50");

        // PASO 5: Abrir el selector de momento del día
        WebElement selectMomento = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[1]/div[2]/select"
        ));
        selectMomento.click();

        // PASO 6: Seleccionar "Ayunas" 
        WebElement optionAyunas = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[1]/div[2]/select/option[2]"
        ));
        optionAyunas.click();

        // PASO 7: Escribir observaciones
        WebElement textareaObservaciones = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[2]/textarea"
        ));
        textareaObservaciones.sendKeys("Prueba automatizada hipoglucemia");

        // PASO 8: Clic en "Registrar" 
        WebElement btnRegistrar = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[3]/button"
        ));
        btnRegistrar.click();

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        // PASO 9: Clic en "Confirmar" del Modal 1
        WebElement btnConfirmar1 = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div[2]/div/div/button[2]"
        ));
        btnConfirmar1.click();

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        // PASO 10: Clic en "Confirmar" del Modal 2
        WebElement btnConfirmar2 = driver.findElement(By.xpath(
            "/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div[2]/div/div/button[2]"
        ));
        btnConfirmar2.click();

        // Esperamos que el backend registre la glucosa, evalúe hipoglucemia, genere la alerta y Angular muestre el modal
        try { TimeUnit.SECONDS.sleep(8); } catch (InterruptedException e) { e.printStackTrace(); }

        // ------------------------------------------
        // VERIFICACIÓN — El modal de alerta de hipoglucemia debe aparecer
        // ------------------------------------------

        // ASSERT: El botón "Entendido" del modal de alerta es visible
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement btnEntendido = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(),'Entendido')]")
            )
        );

        Assert.assertTrue(
            "El modal de alerta de hipoglucemia no apareció",
            btnEntendido.isDisplayed()
        );
    }

    @AfterTest
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}
