package me.sieric.webdriver;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuesPage {

    @FindBy(className = "issueId")
    private WebElement lastIssue;

    public void openLastIssue(@NotNull WebDriverWait wait) {
        wait.until(it -> lastIssue.isDisplayed());
        lastIssue.click();
    }
}
