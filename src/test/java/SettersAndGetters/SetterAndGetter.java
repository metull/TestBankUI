package SettersAndGetters;

public class SetterAndGetter {
    private static String urlBank = "//h3[.='Банк «Открытие» — вклады, кредитные и дебетовые ...']/parent::a//cite[.='www.open.ru']";

    public static String getUrlBank() {
        return urlBank;
    }

    public static void setUrlBank(String getUrlBank) {
        urlBank = getUrlBank;
    }

}
