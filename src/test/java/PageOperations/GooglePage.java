package PageOperations;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

public class GooglePage extends Page {

    private String searchButtonLocated = "q";
    private String fieldFromInput = "btnK";

    public GooglePage(RemoteWebDriver driver) {
        super(driver);
    }

    private WebElement getSearchButton() {
        return byName(searchButtonLocated, "Получаем поле для ввода");
    }

    private WebElement getButtonFromClick() {
        return byName(fieldFromInput, "Получаем кнопку");
    }

    public void fillSearchField(String text) {
        Reporter.log(" <br><b>Вводим текст \"" + text + "\" </b>");
        getSearchButton().sendKeys(text);
    }

    public void clickButtonFindInGoogle() {
        Reporter.log(" <br><b>Нажимаем на кнопку Поиск в Google</b>");
        getButtonFromClick().click();
    }

    public void goToPage() {
       driver.get("https://www.google.com");
    }

    private WebElement findLink(String url) {
        return byXpath(url, "Получаем ссылку");
    }

    public void clickOnLink(String url) {
        findLink("//a[@href='" + url + "']").click();
    }
}
