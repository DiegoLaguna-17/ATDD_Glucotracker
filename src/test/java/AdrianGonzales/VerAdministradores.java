package AdrianGonzales;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerAdministradores {
	
	private WebDriver driver;
	
	@BeforeTest
	public void setDriver() {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	}
	
	@Test
	public void verAdministrador() {
		//PREPARACION
		driver.get("http://localhost:4200");
		try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
		//LOGICA
    	//PASO 1: Iniciar sesión como administrador
    	WebElement txtUsuario = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"));
    	txtUsuario.sendKeys("adrian.gonzales.f@ucb.edu.bo");
    	
    	WebElement txtContrasenia = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
    	txtContrasenia.sendKeys("AdrianGonzales14#");
    	
    	WebElement botonIngresar = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
    	botonIngresar.click();
    	
    	try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    
		//PASO 2: Buscar y ver administrador
    	WebElement menuAdmin = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/button[3]/span[1]"));
    	menuAdmin.click();
    	
    	WebElement botonAdmActivos = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/nav/div[1]/a[1]"));
    	botonAdmActivos.click();
    	
    	
    	try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    	
    	WebElement txtBuscar = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-admins-activos/section/input"));
    	txtBuscar.sendKeys("Luciana Yahuita");
    	
    	WebElement botonVerAdmin = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-admins-activos/section/div/app-card-admin-a/article/button"));
    	botonVerAdmin.click();
    	
    	try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    	
    	WebElement labelTelefono = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-admin-detalle/section/article/div/div[5]/span[2]"));
    	Assert.assertEquals("75816002",labelTelefono.getText());
	}
	@AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
	
	
}
