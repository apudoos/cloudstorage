package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePageCred {

    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id="nav-credentials-tab")
    private WebElement credNotesTab;

    @FindBy(id="addCred")
    private WebElement addCredButton;

    @FindBy(id="credential-url")
    private WebElement credUrl;

    @FindBy(id="credential-username")
    private WebElement credUsername;

    @FindBy(id="credential-password")
    private WebElement credPassword;

    @FindBy(id="credSubmitClick")
    private WebElement credSubmitButton;

    @FindBy(id="successMsg")
    private WebElement successMsg;

    @FindBy(id="clickSuccess")
    private WebElement clickSuccess;

    @FindBy(id="credentialTable")
    WebElement tableCredElement;

    @FindBy(id="credEditButton1")
    WebElement editCred;

    @FindBy(id="credDeleteButton2")
    WebElement deleteCred;

    public HomePageCred(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOut() {
        logoutButton.click();
    }

    public String navCredTabClick(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nav-credentials-tab"))));
        credNotesTab.click();
        return credNotesTab.getText();
    }

    public void addCredButtonClick(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> webDriver.findElement(By.id("titleUrl")));
        wait.until(webDriver -> webDriver.findElement(By.id("titleUsername")));
        wait.until(webDriver -> webDriver.findElement(By.id("titlePassword")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addCred"))));
        addCredButton.click();
    }

    public void addCred(String credUrlInput, String credUserNameInput, String credPasswordInput, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credential-url"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credential-username"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credential-password"))));
        wait.until(webDriver -> webDriver.findElement(By.id("credentialSubmit")));
        wait.until(webDriver -> webDriver.findElement(By.id("credSubmitClick")));
        credUrl.sendKeys(credUrlInput);
        credUsername.sendKeys(credUserNameInput);
        credPassword.sendKeys(credPasswordInput);

        credSubmitButton.click();
    }

    public String successMsgText() {
        return successMsg.getText();
    }

    public void successGoBack(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("clickSuccess"))));
        clickSuccess.click();
    }

    public void clickEditCred() {
        editCred.click();
    }

    public String readCredList(WebDriver driver, Integer index) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credTBody"))));

        List<WebElement> tBodyElement = tableCredElement.findElements(By.id("credTBody"));
        List<WebElement> trElements = tBodyElement.get(0).findElements(By.tagName(("tr")));
        List<WebElement> tdElements = trElements.get(index).findElements(By.tagName(("td")));
        List<WebElement> thElements = trElements.get(index).findElements(By.tagName(("th")));

        return  thElements.get(0).getText()
                + " " + tdElements.get(1).getText()
                + " " + tdElements.get(2).getText();

    }

    public void editCred(String credUrlInput, String credUserNameInput, String credPasswordInput, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credential-url"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credential-username"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("credential-password"))));
        wait.until(webDriver -> webDriver.findElement(By.id("credentialSubmit")));
        wait.until(webDriver -> webDriver.findElement(By.id("credSubmitClick")));
        credUrl.clear();
        credUrl.sendKeys(credUrlInput);
        credUsername.clear();
        credUsername.sendKeys(credUserNameInput);
        credPassword.clear();
        credPassword.sendKeys(credPasswordInput);

        credSubmitButton.click();
    }

    public void deleteCred() {
        deleteCred.click();
    }

}
