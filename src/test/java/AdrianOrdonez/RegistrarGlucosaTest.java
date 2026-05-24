package AdrianOrdonez;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrarGlucosaTest {
    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void registrarGlucosa() {

        // PREPARACIÓN DE LA PRUEBA

        driver.get("http://localhost:4200");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // LÓGICA DE LA PRUEBA
        
        // PASO 1: Ingresar email de Login
        WebElement emailLogin = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"));
        emailLogin.sendKeys("atdd@gmail.com");

        // PASO 2: Ingresar password de Login
        WebElement passwordLogin = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
        passwordLogin.sendKeys("123456");

        // PASO 3: Clic en botón de Login
        WebElement loginBtn = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
        loginBtn.click();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 4: Ingresar nivel de glucosa
        WebElement nivelGlucosa = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[1]/div[1]/input"));
        nivelGlucosa.sendKeys("2.0");

        // PASO 5: Abrir el selector de momento del día
        WebElement momentoDelDiaSelect = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[1]/div[2]/select"));
        momentoDelDiaSelect.click();

        // PASO 6: Seleccionar momento del día -> AYUNAS
        WebElement momentoAyunas = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[1]/div[2]/select/option[2]"));
        momentoAyunas.click();

        // PASO 7: Ingresar las observaciones
        WebElement observaciones = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[2]/textarea"));
        observaciones.sendKeys("Sin observaciones");

        // PASO 8: Clic en 'Registrar'
        WebElement registrarBtn = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div/div/form/div[3]/button"));
        registrarBtn.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 9: Clic en 'Confirmar'
        WebElement confirmarBtn = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div[2]/div/div/button[2]"));
        confirmarBtn.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 10: Volver a hacer clic en 'Confirmar'
        WebElement confirmarSeguroBtn = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div[2]/div/div/button[2]"));
        confirmarSeguroBtn.click();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ASSERT: Verificar que el botón 'Entendido' sea visible
        // (significa que el registro de glucosa fue exitoso)
        WebElement entendidoBtn = driver.findElement(By.xpath("/html/body/app-root/app-paciente-shell/div/section/div/app-registrar-glucosa/div[2]/div/button"));
        
        Assert.assertTrue("El botón Entendido no fue encontrado", entendidoBtn.isDisplayed());
    }

    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
}
