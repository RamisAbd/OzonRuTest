package ru.aplana.steps;

        import cucumber.api.java.ru.И;
        import cucumber.api.java.ru.Когда;
        import cucumber.api.java.ru.Тогда;

public class CucumberSteps {
    MainSteps mainSteps = new MainSteps();
    @Когда("Выполнен поиск по \"(.*)\"$")
    public void выполняемПоиск(String text) {
        mainSteps.select(text);
    }
    @И("Ограничена цена – до (.*)$")
    public void ограничкнаЦенаДо(String text) {
        mainSteps.priceLimit(text);
    }

    @И("Отмечен чекбокс – (.*)$")
    public  void выбираемВысокое(String text)  {
        mainSteps.checkbox(text);

    }
    @И("Бренды : (.*), (.*)$")
    public void брендыSamsungBeats(String text, String text2) {
        mainSteps.selectBeats(text, text2);
    }
    @И("Отмечен чекбокс: (.*)$")
    public void выбираем3ГБ(String text)  {
        mainSteps.selectRAM(text);
    }

    @Тогда("^Добавлено в корзину первые (\\d+) (.*) товаров и сохранение названия$")
    public void добавленоВКорзинуПервыеНечетныхТоваровИСохранениеНазвания(int arg0, String arg1)  {
        mainSteps.addCart(arg0,arg1);

    }

    @Тогда("^Переход в корзину$")
    public void переходВКорзину() {
        mainSteps.cart();
    }

    @И("^Проверяются товары$")
    public void проверяютсяТовары() {
        mainSteps.equalsNAme();
        mainSteps.nameAndPrice();
    }

    @И("^Проверяется, что отображается текст «Ваша корзина  - (.*)»$")
    public void проверяетсяЧтоОтображаетсяТекстВашаКорзинаТоваров(String arg) {
        mainSteps.infoCart(arg);

    }

    @И("^Удаляются все товары из корзины$")
    public void удаляютсяВсеТоварыИзКорзины() {
        mainSteps.deleteAll();
    }

    @И("^Проверяетя, что корзина не содержит никаких товаров$")
    public void проверяетяЧтоКорзинаНеСодержитНикакихТоваровс() {
        mainSteps.emptyCart();
    }


}
