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

public class RegistrarAdministradorTest {

    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void testRegistrarAdmin() {
        
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
            TimeUnit.SECONDS.sleep(10);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        // PASO 4: Clic en 'Administradores'
        WebElement seccionAdministradores = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/button[3]"));
        seccionAdministradores.click();

        // PASO 5: Clic en 'Agregar'
        WebElement opcionAgregar = driver.findElement(By.xpath("//*[@id=\"submenu-admin\"]/a[2]"));
        opcionAgregar.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PASO 6: Ingresar nombre completo
        WebElement nombreCompleto = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[1]/input"));
        nombreCompleto.sendKeys("nombre completo atdd");

        // PASO 7: Ingresar fecha de nacimiento
        WebElement fechaNacimiento = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[2]/div[1]/input"));
        fechaNacimiento.sendKeys("1990-01-01");

        // PASO 8: Ingresar teléfono
        WebElement telefono = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[2]/div[2]/input"));
        telefono.sendKeys("88888888");

        // PASO 9: Seleccionar Cargo -> 'Admin'
        WebElement cargo = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[3]/div[1]/select"));
        cargo.click();

        WebElement opcionCargoAdmin = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[3]/div[1]/select/option[2]"));
        opcionCargoAdmin.click();

        // PASO 10: Ingresar email de registro
        WebElement emailRegistroAdmin = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[3]/div[2]/input"));
        emailRegistroAdmin.sendKeys("correo_atdd@email.com");

        // PASO 11: Ingresar password de registro
        WebElement passwordRegistroAdmin = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[3]/div[3]/input"));
        passwordRegistroAdmin.sendKeys("123456");

        // PASO 12: Clic en 'Registrar'
        WebElement registroAdminBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div/div/form/div[4]/button"));
        registroAdminBtn.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ASSERT: Verificar que el botón "Aceptar" sea visible
        // (significa que el registro del administrador fue exitoso)
        WebElement aceptarBtn = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-agregar/div[2]/div/div/div[2]/button"));

        Assert.assertTrue("El botón Aceptar no fue encontrado", aceptarBtn.isDisplayed());
    }
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
}