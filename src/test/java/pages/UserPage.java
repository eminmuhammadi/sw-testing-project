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

class UserPage extends PageBase {

    private By bioDiv = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[5]/div/div[1]");
    private By bioTextarea = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[5]/div/div[1]/textarea");
    
    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public UserPage(WebDriver driver) {
        super(driver);
    }    

    /*
     |-------------------------------
     | getBioText
     |-------------------------------
    */
    public String getBioText() {
        this.waitAndReturnElement(bioDiv).click();

        return this.waitAndReturnElement(bioTextarea).getText();
    }
}
