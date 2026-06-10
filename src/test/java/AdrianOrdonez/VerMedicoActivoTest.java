package AdrianOrdonez;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerMedicoActivoTest {
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verificarDatosVisiblesDeMedicoActivo() {
        // --- PREPARACIÓN DE LA PRUEBA ---
        driver.get("http://localhost:4200");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // --- LÓGICA DE LA PRUEBA ---

        // PASO 1: Ingresar email de administrador
        WebElement emailLogin = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"));
        emailLogin.sendKeys("fabriadri2705@gmail.com");

        // PASO 2: Ingresar contraseña de administrador
        WebElement passwordLogin = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
        passwordLogin.sendKeys("fabriadri2705");

        // PASO 3: Hacer clic en 'Ingresar'
        WebElement ingresarBtn = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
        ingresarBtn.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 4: Hacer clic en la pestaña 'Medicos'
        WebElement medicosTab = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/button[2]"));
        medicosTab.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 5: Hacer clic en la pestaña 'Activos'
        WebElement activosTab = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/div[1]/a[1]"));
        activosTab.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // PASO 6: Hacer clic en 'Ver Medico' en un médico activo
        WebElement verMedicoBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-medicos-activos/div/div/app-card-medico-a[1]/article/button"));
        verMedicoBtn.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // --- ASSERT ---
        // Verificar que los Datos Personales, 
        // Matrícula, Departamento y Carnet Profesional
        // sean visibles
        WebElement datosPersonales = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-activo/div/div[1]"));
        WebElement matricula = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-activo/div/div[2]"));
        WebElement departamento = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-activo/div/div[3]/div[1]"));
        WebElement carnetProfesional = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-activo/div/div[3]/div[2]"));

        // Desliza la vista de la página 1000 píxeles hacia abajo con Javascript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue("La sección Datos Personales no fue encontrada", datosPersonales.isDisplayed());
        Assert.assertTrue("La sección Matrícula no fue encontrada", matricula.isDisplayed());
        Assert.assertTrue("La sección Departamento no fue encontrada", departamento.isDisplayed());
        Assert.assertTrue("La sección Carnet Profesional no fue encontrada", carnetProfesional.isDisplayed());
    }

    @AfterTest
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}
