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

        driver.get("http://localhost:4200");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // INPUT USUARIO
        WebElement inputUsuario = driver.findElement(By.xpath("//input[@formcontrolname='usuario']"));
        inputUsuario.sendKeys("usuario_invalido");

        // INPUT PASSWORD
        WebElement inputPassword = driver.findElement(
            By.xpath("//input[@formcontrolname='contrasena']")
        );
        inputPassword.sendKeys("123456");

        // BOTÓN LOGIN
        WebElement btnLogin = driver.findElement(
            By.xpath("//button[contains(text(),'Ingresar')]")
        );
        btnLogin.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // ESPERAR MODAL DE ERROR
        WebElement modalError = driver.findElement(
            
                By.xpath("//div[contains(@class,'modal--error')]")
            
        );

        // VALIDAR QUE SE MUESTRA
        assertTrue(modalError.isDisplayed());

        // VALIDAR TÍTULO
        WebElement titulo = driver.findElement(
            By.xpath("//div[contains(@class,'modal--error')]//h2")
        );
        assertEquals("Error de Inicio de Sesión", titulo.getText().trim());

        // VALIDAR MENSAJE (opcional pero recomendado)
        WebElement mensaje = driver.findElement(
            By.xpath("//div[contains(@class,'modal--error')]//p")
        );

        assertTrue(mensaje.getText().length() > 0); // hay mensaje

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}