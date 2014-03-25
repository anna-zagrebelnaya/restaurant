package com.engagepoint.acceptancetest;

import com.engagepoint.acceptancetest.base.pages.UIBootstrapBasePage;
import com.engagepoint.acceptancetest.base.steps.JbehaveBaseSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyJBehaveSteps {

    private static final String TABLE_TAG = "table";

    private String subject;

    @Steps
    private JbehaveBaseSteps jbehaveBase;

    private transient UIBootstrapBasePage uIBootstrapBasePage;

    private WebElement getTableElement(String id) {
        By tableBy = jbehaveBase.findVisibleElementAndGetSelector(id);
        WebElement tableElement = uIBootstrapBasePage.getDriver().findElement(tableBy);
        if (!tableElement.getTagName().contentEquals(TABLE_TAG))
            tableElement = tableElement.findElement(By.tagName(TABLE_TAG));
        return tableElement;
    }

    @Given("subject")
    public void givenSubject(@Named("subject") String subject) {
        this.subject = subject;
    }

    @When("in table '$tableId' user edits row with <subject>")
    public void whenInTableChoosesRowWithTestName(String tableId) {
        WebElement tableElement = getTableElement(tableId);
        WebElement row = tableElement.findElement(By.xpath("//tr[.//*[contains(text(),'" + subject + "')]]//td//button[.//span[contains(@class,'ui-icon-search')]]"));
        row.click();
    }
}
