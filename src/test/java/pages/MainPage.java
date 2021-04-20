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
import org.openqa.selenium.Cookie;

class MainPage extends PageBase {
    
	ConfigFileReader configFileReader;

    private By joinButton  = By.xpath("//a[@href='/join']");
    private By loginButton = By.xpath("//a[@href='/log_in']");
    private By loginLink   = By.xpath("//*[@id='registration_forms']//a[contains(text(), 'Log in')]");
    private By joinLink    = By.xpath("//*[@id='registration_forms']//a[contains(text(), 'Join')]");

    /*
     |-------------------------------
     | Main page
     |-------------------------------
    */
    public MainPage(WebDriver driver) {
        super(driver);

        configFileReader = new ConfigFileReader();
        this.driver.get((configFileReader.init()).getProperty("baseUrl"));
    }  
    
    /*
     |-------------------------------
     | Get title
     |-------------------------------
    */
    public String getTitle() {
        return (this.driver).getTitle();
    }

    /*
     |-------------------------------
     | Go to login page
     |-------------------------------
    */
    public LoginPage goToLogin() {
        this.waitAndReturnElement(joinButton).click();
        this.waitAndReturnElement(loginLink).click();

        return new LoginPage(this.driver);
    }

    /*
     |-------------------------------
     | Go to register page
     |-------------------------------
    */
    public RegisterPage goToRegister() {
        this.waitAndReturnElement(loginButton).click();
        this.waitAndReturnElement(joinLink).click();

        return new RegisterPage(this.driver);
    }

    /*
     |-------------------------------
     | Go to register page
     |-------------------------------
    */
    public DashboardPage autoLoginSteps() {
        MainPage mainPage = new MainPage(this.driver);
        LoginPage loginPage = mainPage.goToLogin();

        configFileReader = new ConfigFileReader();

        // User credentials
        DashboardPage dashboardPage = loginPage.login(
            (configFileReader.init()).getProperty("email"), 
            (configFileReader.init()).getProperty("password")
        );
        
        return dashboardPage;
    }

    /*
     |------------------------------------
     | Go to dashboard without Logging in
     |------------------------------------
    */
    public DashboardPage goLoginWithCookie() {
        configFileReader = new ConfigFileReader();

        this.driver.manage().addCookie(new Cookie(
            "vimeo", 
            (configFileReader.init()).getProperty("cookie_name_vimeo")
        ));

        this.driver.get((configFileReader.init()).getProperty("baseUrl"));

        return new DashboardPage(this.driver);
    }
}
