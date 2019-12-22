package me.sieric.webdriver;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage {

    @FindBy(id = "id_l.I.ic.icr.it.issSum")
    private WebElement summary;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div")
    private WebElement description;

    public Issue getIssue( @NotNull WebDriverWait wait) {
        wait.until(it -> summary.isDisplayed());
        wait.until(it -> description.isDisplayed());
        return new Issue(summary.getText(), description.getText());
    }

    public String getSummary() {
        return summary.getText();
    }

    public String getDescription() {
        return description.getText();
    }
}
