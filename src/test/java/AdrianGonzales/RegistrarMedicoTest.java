package AdrianGonzales;

import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class RegistrarMedicoTest {
	
private WebDriver driver;
    
    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
	// --- TEST CASES RELACIONADOS ---

	/*
		- Nº 8: Verificar que al presionar el botón de “REGISTRATE COMO MÉDICO” abra el formulario de solicitud de registro de médicos

		- Nº 11: Verificar que el campo “Correo” en el formulario de solicitud de registro de médicos valide que el valor ingresado sea correspondiente a una dirección de correo válida

		- Nº 13: Verificar que el campo “Matrícula Profesional” en el formulario de solicitud de registro de médicos valide que el documento subido sea un documento PDF

		- Nº 14: Verificar que el campo “Carnet Profesional” en el formulario de solicitud de registro de médicos valide que el documento subido sea un documento válido solo para imágenes JPG, JPEG y PNG
	*/
    @Test
    public void testResgistrarMedico() {
    	// --- PREPARACIÓN DE LA PRUEBA ---
    	driver.get("http://localhost:4200");
    	
    	try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    	// --- LÓGICA DE LA PRUEBA ---
    	// PASO 1: Hacer clic en el boton de registrar medico
    	WebElement botonMedico = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[1]/div/div/button[2]"));
    	botonMedico.click();
    	
    	try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    	
    	// PASO 2: Llenar formulario
    	WebElement txtNombreCompleto = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[1]/div[1]/input"));
    	txtNombreCompleto.sendKeys("Steven Strange");
    	
    	WebElement dateFechaNac = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[1]/div[2]/div[1]/input"));
    	dateFechaNac.sendKeys("1989-02-21");
    	
    	WebElement txtTelefono = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[1]/div[2]/div[2]/input"));
    	txtTelefono.sendKeys("77884523");
    	
    	WebElement txtDepartamento = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[1]/div[2]/div[3]/input"));
    	txtDepartamento.sendKeys("Santa Cruz");
    	
    	WebElement txtCorreo = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[1]/div[3]/input"));
    	txtCorreo.sendKeys("sstrange@attdexample.com");
    	
    	WebElement txtContrasenia = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[1]/div[4]/input"));
    	txtContrasenia.sendKeys("SdywfmCS1996!");
    	
    	String rutaMatriculaPDF = System.getProperty("user.dir")+"/src/test/java/AdrianGonzales/Files/matriculaProfesional.pdf";
    	WebElement fileMatricula = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[2]/div[1]/div[1]/input"));
    	fileMatricula.sendKeys(rutaMatriculaPDF);
    	
    	String rutaCarnetJPG = System.getProperty("user.dir")+"/src/test/java/AdrianGonzales/Files/carnetProfesional.jpg";
    	WebElement fileCarnet = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[2]/div[1]/div[2]/input"));
    	fileCarnet.sendKeys(rutaCarnetJPG);
    	
    	WebElement menuEspecialidad = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[2]/div[2]/select"));
    	menuEspecialidad.click();
    	WebElement opcCardiologo = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[2]/div[2]/select/option[3]"));
    	opcCardiologo.click();
    	
    	try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	// --- ASSERT ---
		// Verificar que el botón "Enviar" esté activado
    	WebElement botonEnviar = driver.findElement(By.xpath("/html/body/app-root/app-solicitar-medico/div/div/form/div[3]/button[1]"));
    	Assert.assertTrue("El botón Enviar no esta activado", botonEnviar.isEnabled());
    }
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
    
}
