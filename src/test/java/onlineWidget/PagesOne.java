package onlineWidget;

import Common.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PagesOne {

    public class Online extends BaseTest {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy_HH_mm_SS");
        String date = DATE_FORMAT.format(today);

        @Parameters({"online"})
        @Test(groups = { "smoke" })
        public void UiTest(String online) {
            app.uiTest().TestForBank();
        }
    }
}
