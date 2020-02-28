package ru.aplana.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.steps.BaseSteps;
import java.util.*;

import static org.junit.Assert.assertEquals;


public class MainPage extends BasePage {
    public MainPage () {
        PageFactory.initElements(BaseSteps.getDriver(),this);
    }
    Map<String,Integer> nameAndPrice =new HashMap<>();
    List<String> name = new ArrayList<>();
    @FindBy (xpath = "//div[contains(text(), 'Цена')]/..//label[contains(text(), 'до')]/..//input")
    public WebElement priceLimit;
    @FindBy (xpath = "//input[@name='search']")
    public WebElement search;
    @FindBy(xpath = "//a[@data-widget='cart']")
    public WebElement cart;
    @FindBy(xpath = "//span[contains(text(),'Удалить выбранные')]")
    public WebElement deleteAll;
    @FindBy(xpath = "//div[contains(text(),'Удалить')]/../..")
    public WebElement delete;
    @FindBy(xpath = "//h1[contains(text(),'Корзина пуста')]")
    public WebElement emptyСart;
    @FindBy(xpath = "//span[@data-test-id='filter-block-brand-show-all']")
    public WebElement menuItemFilter;


    public void checkCartElem() {

        for (int i=0;i<name.size();i++) {
            WebElement nameCart = BaseSteps.getDriver().findElement(By.xpath("//span[contains(text(),'" + name.get(i) + "')]"));
           new WebDriverWait(BaseSteps.getDriver(),10).until(ExpectedConditions.visibilityOf(nameCart));

        }

    }
    public  void setNameAndPrice(){
        new BaseSteps().saveToFileAllure(nameAndPrice);
    }
    public void closeAdvertising(){
        WebElement element = BaseSteps.getDriver().findElement(By.xpath("//button[@aria-label= 'Закрыть сообщение']"));
        new WebDriverWait(BaseSteps.getDriver(), 30).until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)BaseSteps.getDriver()).executeScript("arguments[0].click();", element);
    }
    public void setSearch(String text){
        closeAdvertising();
        setText(search,text+"\n");
    }

    public void setPriceLimit(String text){
        ((JavascriptExecutor) BaseSteps.getDriver()).executeScript("arguments[0].scrollIntoView(true);", priceLimit);
        new WebDriverWait(BaseSteps.getDriver(),30).until(ExpectedConditions.visibilityOf(priceLimit));
        priceLimit.sendKeys(Keys.CONTROL + "a");
        priceLimit.sendKeys(text +"\n");
        waitPageLoaded();
    }
    public void menuName(){
        new WebDriverWait(BaseSteps.getDriver(),30).until(ExpectedConditions.visibilityOf(menuItemFilter));
        JavascriptExecutor executor = (JavascriptExecutor)BaseSteps.getDriver();
        executor.executeScript("arguments[0].click();", menuItemFilter);
    }
    public void selectElemText(String text) {
        WebElement element = BaseSteps.getDriver().findElement(By.xpath("//span[text()='"+text+"']/.."));
        new WebDriverWait(BaseSteps.getDriver(),30).until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor executor = (JavascriptExecutor)BaseSteps.getDriver();
        executor.executeScript("arguments[0].click();", element);

        waitPageLoaded();

    }

    public void addToCart(int count, String text){
        switch (text) {
            case "нечетных":
                for (int i = 1; i < count+1; i++) {
                    WebElement nameItem = BaseSteps.getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" +(2*i-1)+ "]"));
                    ((JavascriptExecutor) BaseSteps.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nameItem);
                    name.add(nameItem.getText());
                    WebElement price = BaseSteps.getDriver().findElement(By.xpath("(//span[@data-test-id='tile-price'])[" +(2*i-1)+ "]"));
                    nameAndPrice.put(nameItem.getText(),Integer.parseInt(price.getText().replaceAll("[\u2009₽]","")));
                    WebElement buyItem = BaseSteps.getDriver().findElement(By.xpath("(//button[@qa-id='tile-buy-button'])[" +i+ "]"));
                    ((JavascriptExecutor)BaseSteps.getDriver()).executeScript("arguments[0].click();", buyItem);
                    waitPageLoaded();

                }
                break;
            case "четных":
                for (int i = 2; i < count+2; i++) {
                    WebElement nameItem = BaseSteps.getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" +(2*i-2)+ "]"));
                    ((JavascriptExecutor) BaseSteps.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nameItem);
                    name.add(nameItem.getText());
                    WebElement price = BaseSteps.getDriver().findElement(By.xpath("(//span[@data-test-id='tile-price'])[" +(2*i-2)+ "]"));

                    nameAndPrice.put(nameItem.getText(),Integer.parseInt(price.getText().replaceAll("[\u2009₽]","")));
                    WebElement buyItem = BaseSteps.getDriver().findElement(By.xpath("(//button[@qa-id='tile-buy-button'])[" +i+ "]"));
                    ((JavascriptExecutor)BaseSteps.getDriver()).executeScript("arguments[0].click();", buyItem);
                    waitPageLoaded();
                }
                break;
            default:
                throw new AssertionError("Нет аргумента четности");
        }

    }

    public  void cartClick(){
        ((JavascriptExecutor) BaseSteps.getDriver()).executeScript("arguments[0].scrollIntoView(true);", cart);
        new WebDriverWait(BaseSteps.getDriver(),30).until(ExpectedConditions.elementToBeClickable(cart));
        cart.click();
    }

    public void deleteAllElement(){
        new WebDriverWait(BaseSteps.getDriver(), 30).until(ExpectedConditions.visibilityOf(deleteAll));
        ((JavascriptExecutor)BaseSteps.getDriver()).executeScript("arguments[0].click();", deleteAll);
        new WebDriverWait(BaseSteps.getDriver(), 30).until(ExpectedConditions.elementToBeClickable(delete));
        ((JavascriptExecutor)BaseSteps.getDriver()).executeScript("arguments[0].click();", delete);
    }
    public void informationCart(String text){
        WebElement element = BaseSteps.getDriver().findElement(By.xpath("//span[contains(text(),'"+text+"')]"));
        assertEquals(text.replaceAll(" ",""),element.getText().substring(0,9).replaceAll(" ",""));
    }
    public void emptyShoppingCart(){
        new WebDriverWait(BaseSteps.getDriver(), 30).until(ExpectedConditions.visibilityOf(emptyСart));
        assertEquals("Корзина пуста",emptyСart.getText());
    }
}
