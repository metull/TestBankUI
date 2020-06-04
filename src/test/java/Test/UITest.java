package Test;

import Application.ApplicationManager;
import Common.PageOperations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static Check.Check.CheckValue;
import static Check.Check.CheckValueOnEquals;
import static SettersAndGetters.SetterAndGetter.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UITest extends PageOperations {

    protected ApplicationManager app;
    private WebElement element;

    public UITest(RemoteWebDriver driver) {
        super(driver);
        app = new ApplicationManager();
    }

    public void sendText() {
        app.page().byName("q", "Вводим текст \"Открытие\"")
                .sendKeys("Открытие");
    }

    public void clickOnButtonFind() {
        app.page().byName("btnK", "Нажимаем на кнопку \"Поиск в Google\"")
                .click();
    }

    public String checkUrl() {
       return app.page().byXpath(getUrlBank(), "Проверяем наличие ссылки")
                .getText();
    }
    public void clickOnUrlBank() {
       app.page().byXpath(getUrlBank(), "Кликаем на ссылку")
                .click();
    }

    public void getPriceUSDAndEquals() {
        WebElement buyUsd = app.page().byXpath("//tr[2]//td[2]//span","Получаем цену покупки USD");
        WebElement sellUsd = app.page().byXpath("//tr[2]//td[4]//span","Получаем цену продажи USD");
        CheckValueOnEquals(buyUsd.getText(), sellUsd.getText());
    }

    public void getPriceEURAndEquals() {
        WebElement buyUsd = app.page().byXpath("//tr[3]//td[2]//span","Получаем цену покупки USD");
        WebElement sellUsd = app.page().byXpath("//tr[3]//td[4]//span","Получаем цену продажи EUR");
        CheckValueOnEquals(buyUsd.getText(), sellUsd.getText());
    }

    public void TestForBank() {
        app.page().get("https://www.google.com");
        app.uiTest().sendText();
        app.uiTest().clickOnButtonFind();
        CheckValue(checkUrl(), "www.open.ru");
        app.uiTest().clickOnUrlBank();
        app.uiTest().getPriceUSDAndEquals();
        app.uiTest().getPriceEURAndEquals();
    }

}
