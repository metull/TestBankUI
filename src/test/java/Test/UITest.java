package Test;

import Common.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UITest extends BaseTest {

    @Test(groups = { "ui" })
    public void TestForBank() {
        app.googlePage().goToPage();
        app.googlePage().fillSearchField("Открытие");
        app.googlePage().clickButtonFindInGoogle();
        app.googlePage().clickOnLink("https://www.open.ru/");
        Double priceSellEUR = app.bankOpenPage().getPriceSellEUR();
        Double priceBuyEUR = app.bankOpenPage().getPriceBuyEUR();
        Double priceSellUSD = app.bankOpenPage().getPriceSellUSD();
        Double priceBuyUSD = app.bankOpenPage().getPriceBuyUSD();

        assertThat(priceBuyEUR).isGreaterThan( priceSellEUR);
        assertThat(priceBuyUSD).isGreaterThan( priceSellUSD);
    }
}
