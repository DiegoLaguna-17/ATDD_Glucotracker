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
        driver.get("http://localhost:4200");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        WebElement inputCorreo=driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"));
        inputCorreo.sendKeys("diegolagunalevy@gmail.com");

        WebElement inputContrasena=driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
        inputContrasena.sendKeys("GLC!11.esdla.22");

        WebElement btnLogin=driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
        btnLogin.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

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


        WebElement btnPaciente=driver.findElement(By.xpath("(//app-card-paciente-a//button)[1]"));
        btnPaciente.click();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // ... tu código anterior ...
        WebElement btnAceptar=driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div/header/div[2]/button[1]"));
        btnAceptar.click();

        // 1. Esperar a que aparezca el Modal de Confirmación
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 2. Hacer clic en el botón "Activar" dentro del Modal de Confirmación
        // Buscamos el botón que tiene la clase 'btn-aceptar' y el texto 'Activar'
        WebElement btnConfirmarActivacion = driver.findElement(By.xpath("//button[contains(@class, 'btn-aceptar') and text()='Activar']"));
        btnConfirmarActivacion.click();

        // 3. Esperar a que el backend procese y aparezca el Modal de Éxito
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 4. Validar el Modal de Éxito
        // Buscamos el título "¡Éxito!" dentro de la estructura del modal
        WebElement tituloExito = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div[2]/div/div/h3"));
        Assert.assertTrue("El modal de éxito no apareció", tituloExito.isDisplayed());

        // Opcional: Validar que el mensaje diga "activado" (esto asegura que no fue el modal de rechazo)
        WebElement mensajeActivado = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div[2]/div/div/p"));
        Assert.assertTrue("El mensaje de paciente activado no apareció", mensajeActivado.isDisplayed());

        // 5. Hacer clic en el botón "Continuar" para cerrar el modal de éxito
        WebElement btnContinuar = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div[2]/div/div/div/button"));
        btnContinuar.click();

        // Breve pausa para ver cómo se cierra antes de terminar la prueba
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    } // Fin del método aceptarPacienteTest
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }

}
