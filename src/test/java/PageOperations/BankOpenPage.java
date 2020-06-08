package PageOperations;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static Constants.Constants.EXCHANGETABLE;

public class BankOpenPage extends Page {
    public BankOpenPage(RemoteWebDriver driver) {
        super(driver);
    }

    private WebElement getUSDTableCell() {
        return byXpath(EXCHANGETABLE + "//tr[2]//td[2]//span", "Получаем цену покупки USD");
    }

    private WebElement getUSDTableBuy() {
        return byXpath(EXCHANGETABLE + "//tr[2]//td[4]//span", "Получаем цену покупки USD");
    }

    private WebElement getEURTableCell() {
        return byXpath(EXCHANGETABLE + "//tr[3]//td[2]//span", "Получаем цену покупки EUR");
    }

    private WebElement getEURTableBuy() {
        return byXpath(EXCHANGETABLE + "//tr[3]//td[4]//span", "Получаем цену покупки EUR");
    }

    public Double getPriceSellEUR() {
        return Double.parseDouble(getEURTableCell().getText().replace(",", "."));
    }

    public Double getPriceSellUSD() {
        return Double.parseDouble(getUSDTableCell().getText().replace(",", "."));
    }

    public Double getPriceBuyEUR() {
        return Double.parseDouble(getEURTableBuy().getText().replace(",", "."));
    }

    public Double getPriceBuyUSD() {
        return Double.parseDouble(getUSDTableBuy().getText().replace(",", "."));
    }

}
