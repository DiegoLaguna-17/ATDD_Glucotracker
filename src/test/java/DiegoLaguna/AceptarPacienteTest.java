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

        WebElement btnAceptar=driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-detalle-paciente-solicitud/div/header/div[2]/button[1]"));
        assertEquals("Aceptar", btnAceptar.getText());

    }
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }

}
