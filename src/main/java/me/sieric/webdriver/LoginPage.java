package me.sieric.webdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "id_l.L.login")
    private WebElement login;

    @FindBy(id = "id_l.L.password")
    private WebElement password;

    @FindBy(id = "id_l.L.loginButton")
    private WebElement loginButton;

    public void login(String userLogin, String userPassword) {
        login.sendKeys(userLogin);
        password.sendKeys(userPassword);
        loginButton.click();
    }
}
