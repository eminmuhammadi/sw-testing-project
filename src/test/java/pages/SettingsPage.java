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

class SettingsPage extends PageBase {

    private By bioTextarea  = By.xpath("//*[@id=\"wrap\"]/div[2]/main/div/div[1]/div[2]/div[2]/form/div[5]/fieldset/textarea");
    private By submitButton = By.xpath("//input[@type='submit']");
    private By userMenu = By.xpath("//*[@id=\"topnav_menu_avatar\"]");

    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public SettingsPage(WebDriver driver) {
        super(driver);
    }    

    /*
     |-------------------------------
     | editBioText
     |-------------------------------
    */
    public SettingsPage editBioText(String bioText) {
        this.waitAndReturnElement(bioTextarea).clear();
        this.waitAndReturnElement(bioTextarea).sendKeys(bioText);
        this.waitAndReturnElement(submitButton).click();

        return new SettingsPage(this.driver);
    }

    /*
     |-------------------------------
     | Go to User
     |-------------------------------
    */
    public UserPage goToUserPage() {
        this.waitAndReturnElement(userMenu).click();

        return new UserPage(this.driver);
    }
    
}
