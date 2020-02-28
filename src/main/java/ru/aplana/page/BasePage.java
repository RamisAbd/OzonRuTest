package ru.aplana.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.steps.BaseSteps;

import java.util.concurrent.TimeUnit;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    public void waitPageLoaded() {
        new WebDriverWait(BaseSteps.getDriver(), 45)
                .until((ExpectedCondition<Boolean>) webDriver -> !isElementPresent(By.xpath("//div[contains(@class , 'modal-wrapper')]")));
    }

    public boolean isElementPresent(By locator){
        try {
            BaseSteps.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return BaseSteps.getDriver().findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }finally {
            BaseSteps.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }
    }

    public void clicker(WebElement webElement) {
        new WebDriverWait(BaseSteps.getDriver(), 10).until(ExpectedConditions.visibilityOf(webElement));
        ((JavascriptExecutor) BaseSteps.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        new WebDriverWait(BaseSteps.getDriver(), 20).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void setText(WebElement webElement, String text) {
        clicker(webElement);
        webElement.clear();
        for (char i : text.toCharArray()) {
            webElement.sendKeys(String.valueOf(i));
        }
    }
}
