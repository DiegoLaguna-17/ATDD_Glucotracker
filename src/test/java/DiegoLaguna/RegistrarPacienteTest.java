package DiegoLaguna;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistrarPacienteTest {

    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void probaLoginTest() {
        // 1) Preparación de la prueba
        driver.get("http://localhost:4200");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        //2) Lógica de la prueba 
        // Paso 1: Ingresar al formulario de registro de paciente
        WebElement registrarPacienteBtn = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[1]/div/div/button[1]"));
        registrarPacienteBtn .click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        

        //Paso 2: Llenar el formulario de solicitud
        // Llenar nombre
        WebElement nombreFormulario=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[1]/div[1]/input"));
        nombreFormulario.sendKeys("Kael Caballero");
        // Seleccionar fecha de nacimiento
        WebElement fechaNacimiento=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[1]/div[2]/div[1]/input"));
        fechaNacimiento.sendKeys("2004-10-1");
        //Llenar teléfono
        WebElement telefonoPaciente=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[1]/div[2]/div[2]/input"));
        telefonoPaciente.sendKeys("78738851");

        
        //Subir una foto de perfil
        String ruta = System.getProperty("user.dir") 
            + "\\src\\test\\java\\DiegoLaguna\\Files\\fotoPerfil.png";

        WebElement fotoPerfil=driver.findElement(By.xpath("//*[@id=\"foto_perfil\"]"));

        fotoPerfil.sendKeys(ruta);

        //Llenar el nombre del contacto de emergencia
        WebElement nombreEmergencia=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[3]/div[1]/input"));
        nombreEmergencia.sendKeys("Natanael Cano");
        //Llena el teléfono del contacto de emergencia
        WebElement telefonoEmergencia=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[3]/div[2]/input"));
        telefonoEmergencia.sendKeys("77525691");
        //Seleccionar el género
        WebElement cbGenero=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[4]/div/div[1]/select"));
        Select selectGenero=new Select(cbGenero);
        selectGenero.selectByVisibleText("Masculino");
        //Llenar el peso del paciente
        WebElement pesoPaciente=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[4]/div/div[2]/input"));
        pesoPaciente.sendKeys("70");
        //Llenar la altura del paciente
        WebElement alturaPaciente=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[4]/div/div[3]/input"));
        alturaPaciente.sendKeys("1.8");
        //Seleccionar el médico
        WebElement cbMedico=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[5]/div/div/select"));
        Select selectMedico=new Select(cbMedico);
        selectMedico.selectByVisibleText("Dr. Diego Laguna");
        //Indicar la actividad física del paciente
        WebElement cbFisico=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[6]/div/div/select"));
        Select selectFisico=new Select(cbFisico);
        selectFisico.selectByVisibleText("ACTIVO");
        //Indicar la afección que tiene el paciente
        WebElement cbAfeccion=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[7]/div[1]/div[1]/select"));
        Select selectAfeccion=new Select(cbAfeccion);
        selectAfeccion.selectByVisibleText("Diabetes Tipo 1");
        //Indicar el tratamiento que tiene el paciente
        WebElement cbTratamiento=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[7]/div[1]/div[2]/select"));
        Select selectTratamiento=new Select(cbTratamiento);
        selectTratamiento.selectByVisibleText("Insulina");
        //Indicar la dosis del tratamiento del paciente
        WebElement dosisTratamiento=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[7]/div[1]/div[3]/input"));
        dosisTratamiento.sendKeys("5ml/dia");
        //Llenar el correo electronico
        WebElement correo=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[8]/div/div[1]/input"));
        correo.sendKeys("kaelcaballero@ejemplo.com");
        //Llenar la contraseña del paciente
        WebElement contrasena=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[8]/div/div[2]/input"));
        contrasena.sendKeys("Caballe!123");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        //Paso 3: Hacer click en registrar paciente  
        WebElement btnRegistrar= driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[9]/button[1]"));
        btnRegistrar.click();

         try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        // 3) Verificacion de la prueba
        try {
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            
            // Paso 4: Obtener el texto que muestra el alert
            String textoAlert = alert.getText();
            System.out.println("Mensaje del alert capturado: " + textoAlert);
            
            // Paso 5: Comparar que el texto del alert
            Assert.assertEquals("El mensaje del alert no es el esperado", "Paciente registrado exitosamente", textoAlert);
            
            // Paso 6: Aceptar el alert 
            alert.accept();
            
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            Assert.fail("El alert de éxito no se mostró en pantalla.");
        }
        return;
    }
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
}