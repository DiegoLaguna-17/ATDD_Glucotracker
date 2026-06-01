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

public class AceptarMedicoTest {
    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void aceptarMedicoTest() {

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
        emailLogin.sendKeys("fabriadri2705@gmail.com");

        // PASO 2: Ingresar password de Login
        WebElement passwordLogin = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
        passwordLogin.sendKeys("fabriadri2705");

        // PASO 3: Clic en botón de Login
        WebElement loginBtn = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
        loginBtn.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 4: Clic en 'Medicos'
        WebElement seccionMedicos = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/button[2]"));
        seccionMedicos.click();

        // PASO 5: Clic en 'Solicitudes'
        WebElement solicitudesMedicos = driver.findElement(By.xpath("//*[@id=\"submenu-medicos\"]/a[2]"));
        solicitudesMedicos.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 6: Clic en 'Ver medico'
        WebElement verMedicoBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-medicos-solicitudes/section/div/app-card-medico-a[1]/article/button"));
        verMedicoBtn.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 7: Clic en 'Aceptar'
        WebElement aceptarBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-solicitud/div/header/div/button[1]"));
        aceptarBtn.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 8: Clic en 'Activar'
        WebElement activarBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-solicitud/div[2]/div/div[3]/button[2]"));
        activarBtn.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ASSERT: Verificar que el botón 'Continuar' sea visible
        // (significa que la activación del médico fue exitosa)
        WebElement continuarBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-medico-solicitud/div[2]/div/div/div/button"));

        Assert.assertTrue("El botón Continuar no fue encontrado", continuarBtn.isDisplayed());
    }

    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
}
