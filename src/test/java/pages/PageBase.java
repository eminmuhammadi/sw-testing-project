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

class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By bodySelector  = By.tagName("body");

    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    /*
     |-------------------------------
     | waitAndReturnElement
     |-------------------------------
    */
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    } 
 
    /*
     |-------------------------------
     | getBodyText
     |-------------------------------
    */
    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(bodySelector);
        return bodyElement.getText();
    }
}
