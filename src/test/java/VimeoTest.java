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
    public ConfigFileReader configFileReader;
    public RandomString randomString;

    final int BASE_TIMEOUT_SECONDS = 10;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
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
     | Teardown
     |-------------------------------
    */
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /*
     |-------------------------------
     | Static Test
     |-------------------------------
    */
    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        String title = "Vimeo | The world's only all-in-one video solution";

        Assert.assertEquals(title, mainPage.getTitle());
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

        configFileReader = new ConfigFileReader();

        // User credentials
        DashboardPage dashboardPage = loginPage.login(
            (configFileReader.init()).getProperty("email"), 
            (configFileReader.init()).getProperty("password")
        );

        Assert.assertTrue(dashboardPage.getUsernameFromWrap().contains(
            (configFileReader.init()).getProperty("name"))
        );
    }
    
    /*
     |-------------------------------
     | Register
     |-------------------------------
    */
    @Test
    public void testRegister() {
        MainPage mainPage = new MainPage(this.driver);
        RegisterPage registerPage = mainPage.goToRegister();

        configFileReader = new ConfigFileReader();
        randomString = new RandomString();

        String name = randomString.generate(6) + " " + randomString.generate(6);

        // User credentials
        DashboardPage dashboardPage = registerPage.register(
            name,
            randomString.generate(8) + "." + randomString.generate(8) + "@gmail.com", 
            randomString.generate(8) + (configFileReader.init()).getProperty("password")
        );

        Assert.assertTrue(dashboardPage.getUsernameFromWrap().contains(name));
    }

    /*
     |-------------------------------
     | Logout
     |-------------------------------
    */
    @Test
    public void testLogout() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.goToLogin();

        configFileReader = new ConfigFileReader();

        // User credentials
        DashboardPage dashboardPage = loginPage.login(
            (configFileReader.init()).getProperty("email"), 
            (configFileReader.init()).getProperty("password")
        );

        MainPage mainPageAfterLogOut = dashboardPage.logout();
        String title = "Vimeo | The world's only all-in-one video solution";

        Assert.assertEquals(title, mainPage.getTitle());
    }
    
    /*
     |-------------------------------
     | Change bio text
     |-------------------------------
    */
    @Test
    public void testAddBio() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.goToLogin();

        configFileReader = new ConfigFileReader();

        // User credentials
        DashboardPage dashboardPage = loginPage.login(
            (configFileReader.init()).getProperty("email"), 
            (configFileReader.init()).getProperty("password")
        );

        SettingsPage settingsPage = dashboardPage.gotToSettings();

        randomString = new RandomString();
        String bio = randomString.generate(32);
        
        SettingsPage updatedSettingsPage = settingsPage.editBioText(bio);
    }
}
