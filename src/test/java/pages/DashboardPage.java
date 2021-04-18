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
import org.openqa.selenium.interactions.Actions;

class DashboardPage extends PageBase {

    private By userName = By.xpath("//*[@id=\"wrap\"]/div[2]/main/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[1]/div/div/div/div[1]/div/div[1]/section/div/div[1]/div/div/div/div[2]/div/h3");
    private By userMenu = By.xpath("//*[@id=\"topnav_menu_avatar\"]");
    private By logoutButton = By.xpath("//*[@id=\"topnav_user_dropdown\"]//button[contains(text(), 'Log out')]");
    private By linkEditProfile = By.xpath("//*[@id=\"topnav_user_dropdown\"]//a[contains(text(), 'Edit profile')]");
    private By newVideoButton = By.xpath("//*[@id=\"new_video_dropdown_button\"]");
    private By linkUpload = By.xpath("//*[@id=\"topnav_desktop_upload_button\"]");

    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }    

    /*
     |-------------------------------
     | getUsernameFromWrap
     |-------------------------------
    */
    public String getUsernameFromWrap(){
        return this.waitAndReturnElement(userName).getText();
    }

    /*
     |-------------------------------
     | logout
     |-------------------------------
    */
    public MainPage logout() {
        Actions builder = new Actions(this.driver);
        
        builder.moveToElement(this.waitAndReturnElement(userMenu)).perform();
        builder.moveToElement(this.waitAndReturnElement(logoutButton)).click().perform();

        return new MainPage(this.driver);
    }

    /*
     |-------------------------------
     | Go to Settings
     |-------------------------------
    */
    public SettingsPage gotToSettings() {
        Actions builder = new Actions(this.driver);
        
        builder.moveToElement(this.waitAndReturnElement(userMenu)).perform();
        builder.moveToElement(this.waitAndReturnElement(linkEditProfile)).click().perform();

        return new SettingsPage(this.driver);
    }

    /*
     |-------------------------------
     | Go to Upload
     |-------------------------------
    */
    public UploadPage gotToUpload() {
        Actions builder = new Actions(this.driver);
        
        builder.moveToElement(this.waitAndReturnElement(newVideoButton)).perform();
        builder.moveToElement(this.waitAndReturnElement(linkUpload)).click().perform();

        return new UploadPage(this.driver);
    }   
}
