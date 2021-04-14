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

class MainPage extends PageBase {
	ConfigFileReader configFileReader;

    private By joinButton = By.xpath("//a[@href='/join']");
    private By loginLink  = By.className("//a[contains(text(), Log in]");

    /*
     |-------------------------------
     | Main page
     |-------------------------------
    */
    public MainPage(WebDriver driver) {
        super(driver);

        configFileReader = new ConfigFileReader();
        this.driver.get((configFileReader.getProperties()).getProperty("baseUrl"));
    }  
    
    public LoginPage goToLogin() {
        this.waitAndReturnElement(joinButton).click();
        this.waitAndReturnElement(loginLink).click();

        return new LoginPage(this.driver);
    }

}
