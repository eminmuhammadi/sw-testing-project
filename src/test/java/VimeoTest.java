package main;

import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  
import java.util.concurrent.TimeUnit;

public class VimeoTest {
    public WebDriver driver;    
    final int BASE_TIMEOUT_SECONDS = 10;

    /*
     |-------------------------------
     | Warming
     |-------------------------------
    */
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        /*
        |-------------------------------
        | Driver Configuration
        |-------------------------------
        */
        driver = new ChromeDriver();
        
        // configuration path
        WebDriverManager.chromedriver().config().setProperties("config//webdrivermanager.properties");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(BASE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }
 
    /*
     |-------------------------------
     | Login
     |-------------------------------
    */
    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.goToLogin();
        DashboardPage dashboardPage = loginPage.login("#","#");
    }


    /*
     |-------------------------------
     | Teardown
     |-------------------------------
    */
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
