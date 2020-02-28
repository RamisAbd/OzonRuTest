package ru.aplana.steps;

import io.qameta.allure.Step;
import ru.aplana.page.MainPage;

public class MainSteps {
    MainPage mainPage = new MainPage();
    @Step("Выполните поиск по «iphone»")
    public void select(String text){
        mainPage.setSearch(text);
    }
    @Step("Ограничить цену до {0}")
    public void priceLimit(String text){
        mainPage.setPriceLimit(text);
    }
    @Step("Отметить чекбокс – Высокий рейтинг")
    public  void checkbox(String text) {
        mainPage.selectElemText(text);
    }
    @Step("Отметить чекбокс – 3Гб")
    public  void selectRAM(String text){
        mainPage.selectElemText(text);
    }
    @Step("Бренды : Beats, Samsung")
    public  void selectBeats(String text, String text2){
        mainPage.menuName();
        mainPage.selectElemText(text);
        mainPage.selectElemText(text2);
    }
    @Step("Добавить в корзину товары")
    public void addCart(int count, String arg) {
        mainPage.addToCart(count, arg);
    }
    @Step("Перейти в корину")
    public void cart(){
        mainPage.cartClick();
    }
    @Step("Проверка имени")
    public void equalsNAme(){
        mainPage.checkCartElem();
    }
    @Step("Наименование и цена всех товаров")
    public void nameAndPrice(){
        mainPage.setNameAndPrice();
    }
    @Step("Удаление всех элементов")
    public void deleteAll(){
        mainPage.deleteAllElement();
    }
    @Step("Количество товаров в корзине")
    public void infoCart(String text){
        mainPage.informationCart(text);
    }
    @Step("Количество товаров в корзине")
    public void emptyCart(){
        mainPage.emptyShoppingCart();
    }

}
