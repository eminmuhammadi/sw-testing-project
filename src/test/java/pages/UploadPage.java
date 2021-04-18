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
import java.io.File;

class UploadPage extends PageBase {

    private By fileUpload = By.xpath("//input[@type='file']");
    private By submitButton = By.xpath("//*[@id=\"header-pts-button\"]");
    private By fileNameSpan = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/span/div/div[2]/div/div[1]/div/div[1]/h2/span");
    private By dropZone = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[1]/div/div[2]/div[1]");

    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public UploadPage(WebDriver driver) {
        super(driver);
    }    

    /*
     |-------------------------------
     | upload(fileName)
     |-------------------------------
    */
    public UploadPage upload(String fileName) {
    
        this.driver.findElement(fileUpload).sendKeys(
            new File("assets/" + fileName).getAbsolutePath()
        );
        
        return new UploadPage(this.driver);
    }

    public String getVideoFileName() {
        this.waitUntilEnabledAndReturnElement(submitButton).click();

        return this.waitAndReturnElement(fileNameSpan).getText();
    }
}
