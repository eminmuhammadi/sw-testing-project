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
}
