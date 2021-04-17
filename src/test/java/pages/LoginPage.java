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


class LoginPage extends PageBase {

    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By submitButton = By.xpath("//input[@type='submit']");

    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public LoginPage(WebDriver driver) {
        super(driver);
    }    
    
    /*
     |-------------------------------
     | login(email, password)
     |-------------------------------
    */
    public DashboardPage login(String email, String password) {
        this.waitAndReturnElement(emailInput).sendKeys(email);
        this.waitAndReturnElement(passwordInput).sendKeys(password);
        this.waitAndReturnElement(submitButton).click();

        return new DashboardPage(this.driver);
    }
    
}
