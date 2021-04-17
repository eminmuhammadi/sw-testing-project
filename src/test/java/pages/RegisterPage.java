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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;


class RegisterPage extends PageBase {

    private By emailInput    = By.name("email");
    private By nameInput     = By.name("name");
    private By passwordInput = By.name("password");
    private By submitButton  = By.xpath("//input[@type='submit']");
    private By goToDashboardFromLogo = By.xpath("//*[@id=\"header-vimeo-logo\"]");

    // html select tag #1 - What brings you to Vimeo?
    private By selectWhatBrings = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/section[1]/section/div/div/select");
    
    // html select tag #2 - How do you use video?
    private By selectHowToUse = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/section[2]/section/div/div/select");

    // continue (Let's get started!)
    private By buttonContinue = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/footer/div[2]/button");

    // link (Skip)
    private By linkSkip = By.xpath("//a[contains(text(), 'Skip')]");

    // link (No thanks)
    private By linkNoThanks = By.xpath("//a[contains(text(), 'No thanks')]");

    // Link (Dismiss)
    private String linkDismissText = "//a//span[contains(text(), 'Dismiss')]";

    /*
     |-------------------------------
     | constructor
     |-------------------------------
    */
    public RegisterPage(WebDriver driver) {
        super(driver);
    }    
    
    /*
     |----------------------------------
     | register(name, email, password)
     |----------------------------------
    */
    public DashboardPage register(String name, String email, String password) {
        this.waitAndReturnElement(nameInput).sendKeys(name);
        this.waitAndReturnElement(emailInput).sendKeys(email);
        this.waitAndReturnElement(passwordInput).sendKeys(password);
        this.waitAndReturnElement(submitButton).click();
        
        // TODO: Sometimes, it is not working due to Vimeo algo. 
        if((this.driver).findElements(selectWhatBrings).size() != 0) {
            // Dropdowns selector
            Select whatBrings = new Select(this.waitAndReturnElement(selectWhatBrings));
            whatBrings.selectByVisibleText("Record and share videos from my browser");

            // Dropdowns selector
            Select howToUse = new Select(this.waitAndReturnElement(selectHowToUse));
            howToUse.selectByVisibleText("For personal use: as an individual or student");

            // Continue
            this.waitAndReturnElement(buttonContinue).click();
        } else {

            // Solution for Vimeo algo
            this.waitAndReturnElement(linkSkip).click();
        }
        
        // No thanks
        this.waitAndReturnElement(linkNoThanks).click();

        // Go Dashboard
        this.waitAndReturnElement(goToDashboardFromLogo).click();

        // Remove black screen
        ((JavascriptExecutor) this.driver).executeScript("var xPathRes=document.evaluate(\""+linkDismissText+"\",document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null);xPathRes.singleNodeValue.click();");

        return new DashboardPage(this.driver);
    }
    
}
