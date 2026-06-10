package DiegoLaguna;

import static org.junit.Assert.assertEquals;

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

public class AceptarPacienteTest {
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void aceptarPacienteTest(){
        //1) Preparación de la prueba
        driver.get("http://localhost:4200");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        //2) Lógica de la prueba
        // Paso 1: Llenar el campo de correo
        WebElement inputCorreo=driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"));
        inputCorreo.sendKeys("diegolagunalevy@gmail.com");
        // Paso 2: Llenar el campo de contraseña
        WebElement inputContrasena=driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
        inputContrasena.sendKeys("GLC!11.esdla.22");
        // Paso 3: Ingresar haciendo click en login
        WebElement btnLogin=driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
        btnLogin.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        // Paso 4: Ingresar  a la seccion de pacientes solicitantes
        WebElement seccionPacientes =driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/button[1]"));
        seccionPacientes.click();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        WebElement seccionSolicitantes=driver.findElement(By.xpath("//*[@id=\"submenu-pacientes\"]/a[2]"));
        seccionSolicitantes.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // Paso 6: Ingresar al detalle de un paciente solicitante
        WebElement btnPaciente=driver.findElement(By.xpath("(//app-card-paciente-a//button)[1]"));
        btnPaciente.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // Paso 7: Hacer click en el boton aceptar
        WebElement btnAceptar=driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div/header/div[2]/button[1]"));
        btnAceptar.click();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        
        // Paso 8: Hacer click en la confirmación de activación
        WebElement btnConfirmarActivacion = driver.findElement(By.xpath("//button[contains(@class, 'btn-aceptar') and text()='Activar']"));
        btnConfirmarActivacion.click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 3) Verificación de la prueba
        // Paso 9: Encontrar el título y mensaje de activación exitoso
        WebElement tituloExito = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div[2]/div/div/h3"));
        Assert.assertTrue("El modal de éxito no apareció", tituloExito.isDisplayed());

        WebElement mensajeActivado = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div[2]/div/div/p"));
        Assert.assertTrue("El mensaje de paciente activado no apareció", mensajeActivado.isDisplayed());

        // Paso 10: Cerrar el modal
        WebElement btnContinuar = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div[2]/div/div/div/button"));
        btnContinuar.click();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }

}
