package AdrianGonzales;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class BuscarPorCI {
	private WebDriver driver;
	
  	@BeforeTest
  	public void setDriver() {
  		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
  	}
  	@Test
  	public void buscarPorCI() {
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
    	txtBuscar.sendKeys("30");
    	
    	WebElement labelNombre = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-admins-activos/section/div/app-card-admin-a/article/header"));
    	Assert.assertEquals("Pedro",labelNombre.getText());
  
  	}
  	@AfterTest
  	public void closeDriver() {
  		if(driver != null) driver.quit();
  	}

}
