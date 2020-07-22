package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id="addNote")
    private WebElement addNoteButton;

    @FindBy(id="note-title")
    private WebElement noteIdInput;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="noteSubmitClick")
    private WebElement noteSubmitButton;

    @FindBy(id="successMsg")
    private WebElement successMsg;

    @FindBy(id="clickSuccess")
    private WebElement clickSuccess;

    @FindBy(id="userTable")
    WebElement tableElement;

    @FindBy(id="notesEditButton1")
    WebElement editNote;

    @FindBy(id="notesDeleteButton2")
    WebElement deleteNote;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOut() {
        logoutButton.click();
    }

    public String navNotesTabClick(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nav-notes-tab"))));
        navNotesTab.click();
        return navNotesTab.getText();
    }

    public void addNoteButtonClick(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addNote"))));
        addNoteButton.click();
        System.out.println("addNoteButton Clicked");
    }

    public void addNote(String noteIdInputText, String noteDescriptionText, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("note-title"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("note-description"))));
        //wait.until(webDriver -> webDriver.findElement(By.id("note-title")));
        //wait.until(webDriver -> webDriver.findElement(By.id("note-description")));
        wait.until(webDriver -> webDriver.findElement(By.id("noteSubmit")));
        wait.until(webDriver -> webDriver.findElement(By.id("noteSubmitClick")));
        noteIdInput.sendKeys(noteIdInputText);
        noteDescription.sendKeys(noteDescriptionText);

        //wait.until(ExpectedConditions.elementToBeClickable(noteSubmitButton)).click();
        noteSubmitButton.click();
    }

    public String successMsgText() {
        return successMsg.getText();
    }

    public void successGoBack(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("clickSuccess"))));
        clickSuccess.click();
    }

    public void clickEditNote() {
        editNote.click();
    }

    public String readNotesList(WebDriver driver, Integer index) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("notesTBody"))));
        /*wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("tr"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("td"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("th"))));*/
        List<WebElement> tBodyElement = tableElement.findElements(By.id("notesTBody"));
        List<WebElement> trElements = tBodyElement.get(0).findElements(By.tagName(("tr")));
        List<WebElement> tdElements = trElements.get(index).findElements(By.tagName(("td")));
        List<WebElement> thElements = trElements.get(index).findElements(By.tagName(("th")));

        return  thElements.get(0).getText() + " " + tdElements.get(1).getText();

    }

    public void editNote(String noteIdInputText, String noteDescriptionText, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("note-title"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("note-description"))));
        //wait.until(webDriver -> webDriver.findElement(By.id("note-title")));
        //wait.until(webDriver -> webDriver.findElement(By.id("note-description")));
        wait.until(webDriver -> webDriver.findElement(By.id("noteSubmit")));
        wait.until(webDriver -> webDriver.findElement(By.id("noteSubmitClick")));
        noteIdInput.clear();
        noteIdInput.sendKeys(noteIdInputText);
        noteDescription.clear();
        noteDescription.sendKeys(noteDescriptionText);

        //wait.until(ExpectedConditions.elementToBeClickable(noteSubmitButton)).click();
        noteSubmitButton.click();
    }

    public void deleteNote() {
        deleteNote.click();
    }

}
