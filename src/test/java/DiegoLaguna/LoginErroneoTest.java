package DiegoLaguna;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginErroneoTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginCredencialesIncorrectasTest() {
        //1) Preparación de la prueba
        driver.get("http://localhost:4200");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 2) Lógica de la prueba
        // Paso 1: llenar el campo de correo electrónico relacionado a una cuenta válida
        WebElement inputUsuario = driver.findElement(By.xpath("//input[@formcontrolname='usuario']"));
        inputUsuario.sendKeys("diegolagunalevy@gmail.com");

        // Paso 2: llenar el campo de contraseña con un valor erroneo
        WebElement inputPassword = driver.findElement(
            By.xpath("//input[@formcontrolname='contrasena']")
        );
        inputPassword.sendKeys("123456");

        //  Paso 3: Hacer click en el boton de login
        WebElement btnLogin = driver.findElement(
            By.xpath("//button[contains(text(),'Ingresar')]")
        );
        btnLogin.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // 3) Verificación de la prueba
        //Paso 4: Validar que el modal de error esta desplegado
        WebElement modalError = driver.findElement(By.xpath("//html/body/app-root/app-login/div/div"));
        assertTrue(modalError.isDisplayed());

        // Paso 5: Validar  el titulo y mensaje del modal de error
        WebElement titulo = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[1]/h2"));
        assertEquals("Error de Inicio de Sesión", titulo.getText().trim());

        WebElement mensaje = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[2]/p"));

        assertTrue(mensaje.getText().length() > 0);

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}