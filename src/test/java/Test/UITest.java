package Test;

import Application.ApplicationManager;
import Common.PageOperations;
import org.openqa.selenium.remote.RemoteWebDriver;

import static Check.Check.CheckValue;

public class UITest extends PageOperations {

    protected ApplicationManager app;

    public UITest(RemoteWebDriver driver) {
        super(driver);
        app = new ApplicationManager();
    }

    public void TestForBank() {
        app.page().get("https://www.google.com");
        app.caseOne().sendText();
        app.caseOne().clickOnButtonFind();
        CheckValue(app.caseOne().checkUrl(), "www.open.ru");
        app.caseOne().clickOnUrlBank();
        app.caseOne().getPriceUSDAndEquals();
        app.caseOne().getPriceEURAndEquals();
    }

}
