package DiegoLaguna;

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

public class ProbarLoginTest {

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
        
        // 🧪 PASO 1: email
        WebElement email = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[1]/input"));
        email.sendKeys("diegolagunalevy@gmail.com");

        // 🧪 PASO 2: password
        WebElement password = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/label[2]/input"));
        password.sendKeys("GLC!11.esdla.22");

        // 🧪 PASO 3: login
        WebElement loginBtn = driver.findElement(By.xpath("/html/body/app-root/app-login/section/div[2]/div/form/button"));
        loginBtn.click();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        // 🧪 PASO 4: verificación
        WebElement dashboard = driver.findElement(By.xpath("/html/body/app-root/app-admin-shell/div/section/app-pacientes-activos/div/h1"));

        Assert.assertTrue("No se encontró el dashboard", dashboard.isDisplayed());
    }
    
    @AfterTest
    public void closeDriver() {
        if(driver != null) driver.quit();
    }
}