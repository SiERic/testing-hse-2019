package me.sieric.webdriver;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    @FindBy(css = "a[href=\"/issues\"]")
    private WebElement issues;

    @FindBy(xpath = "//*[text()='" + "Create Issue" + "']")
    private WebElement createIssue;

    public void createIssue(@NotNull WebDriverWait wait) {
        wait.until(it -> createIssue.isDisplayed());
        createIssue.click();
    }

    public void openIssues(@NotNull WebDriverWait wait) {
        wait.until(it -> issues.isDisplayed());
        issues.click();
    }
}
