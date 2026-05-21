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
        
        driver.get("http://localhost:4200");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        
        WebElement registrarPacienteBtn = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[1]/div/div/button[1]"));
        registrarPacienteBtn .click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        


        WebElement nombreFormulario=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[1]/div[1]/input"));
        nombreFormulario.sendKeys("Kael Caballero");

        WebElement fechaNacimiento=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[1]/div[2]/div[1]/input"));
        fechaNacimiento.sendKeys("2004-10-1");

        WebElement telefonoPaciente=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[1]/div[2]/div[2]/input"));
        telefonoPaciente.sendKeys("78738851");

        

        String ruta = System.getProperty("user.dir") 
            + "\\src\\test\\java\\DiegoLaguna\\Files\\fotoPerfil.png";

        WebElement fotoPerfil=driver.findElement(By.xpath("//*[@id=\"foto_perfil\"]"));

        fotoPerfil.sendKeys(ruta);


        WebElement nombreEmergencia=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[3]/div[1]/input"));
        nombreEmergencia.sendKeys("Natanael Cano");

        WebElement telefonoEmergencia=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[3]/div[2]/input"));
        telefonoEmergencia.sendKeys("77525691");

        WebElement cbGenero=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[4]/div/div[1]/select"));
        Select selectGenero=new Select(cbGenero);
        selectGenero.selectByVisibleText("Masculino");

        WebElement pesoPaciente=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[4]/div/div[2]/input"));
        pesoPaciente.sendKeys("70");

        WebElement alturaPaciente=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[4]/div/div[3]/input"));
        alturaPaciente.sendKeys("1.8");

        WebElement cbMedico=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[5]/div/div/select"));
        Select selectMedico=new Select(cbMedico);
        selectMedico.selectByVisibleText("Dr. Diego Laguna");

        WebElement cbFisico=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[6]/div/div/select"));
        Select selectFisico=new Select(cbFisico);
        selectFisico.selectByVisibleText("ACTIVO");

        WebElement cbAfeccion=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[7]/div[1]/div[1]/select"));
        Select selectAfeccion=new Select(cbAfeccion);
        selectAfeccion.selectByVisibleText("Diabetes Tipo 1");

        WebElement cbTratamiento=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[7]/div[1]/div[2]/select"));
        Select selectTratamiento=new Select(cbTratamiento);
        selectTratamiento.selectByVisibleText("Insulina");

        WebElement dosisTratamiento=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[7]/div[1]/div[3]/input"));
        dosisTratamiento.sendKeys("5ml/dia");

        WebElement correo=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[8]/div/div[1]/input"));
        correo.sendKeys("kaelcaballero@ejemplo.com");
        WebElement contrasena=driver.findElement(By.xpath("/html/body/app-root/app-solicitar-paciente/div/div/form/div[8]/div/div[2]/input"));
        contrasena.sendKeys("Caballe!123");

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
          

        return;
    }
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
}