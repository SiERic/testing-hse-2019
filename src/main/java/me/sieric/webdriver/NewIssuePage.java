package me.sieric.webdriver;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;

public class NewIssuePage {

    @FindBy(id = "id_l.D.ni.ei.eit.summary")
    private WebElement summary;

    @FindBy(id = "id_l.D.ni.ei.eit.description")
    private WebElement description;

    @FindBy(id = "id_l.D.ni.ei.submitButton_74_0")
    private WebElement createIssue;

    public void createIssue(@NotNull Issue issue, @NotNull WebDriverWait wait) {
        wait.until(it -> summary.isDisplayed());
        summary.clear();
        summary.sendKeys(issue.getSummary());
        description.clear();
        description.sendKeys(issue.getDescription());
        createIssue.click();
    }
}
