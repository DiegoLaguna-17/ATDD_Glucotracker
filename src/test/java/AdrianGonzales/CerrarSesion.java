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


public class CerrarSesion {
	private WebDriver driver;
	
@BeforeTest
  public void setDriver() {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
  }
	
  @Test
  public void cerrarSesion() {
	  //PREPARACION
	  driver.get("http://localhost:4200");
	  try {
          TimeUnit.SECONDS.sleep(5);
      } catch(InterruptedException e) {
          e.printStackTrace();
      }
	  //LOGICA
	  //PASO 1: Iniciar Sesion
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
	  //PASO 2: Cerrar Sesion
	  
	  WebElement botonCerrarSesion = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/div/app-ad-sidebar/div/button"));
	  botonCerrarSesion.click();
	  
	  //ASSERT
	  Assert.assertTrue(driver.getCurrentUrl().contains("login"));
  }
  
  @AfterTest
  public void closeDriver() {
	  if(driver != null) driver.quit();
  }

}
