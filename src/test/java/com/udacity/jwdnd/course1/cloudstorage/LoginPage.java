package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css="#inputUsername")
    private WebElement usernameField;

    @FindBy(css="#inputPassword")
    private WebElement passwordField;

    @FindBy(css="#submit-button")
    private WebElement submitButton;

    @FindBy(css="#error-msg")
    private WebElement errorMessage;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public String errorMessageDisplay() {
        return errorMessage.getText();
    }


}
