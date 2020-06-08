package Common;

import Application.ApplicationManager;
import PageOperations.Page;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.io.File;
import java.util.Date;

import static Application.ApplicationManager.getDriver;

public class Logs extends Page {
    public ApplicationManager app;

    public Logs(RemoteWebDriver driver) {
        super(driver);
        app = new ApplicationManager();
    }

    public void get_Log() {
        LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            // do something useful with the data
        }
    }

    public void failTest(String testName, String platform) {
        Reporter.log(
                "<br><b><div align=\"left\" style=\" height:30px; width:250px; padding: 10px; border: 2px solid gray; margin: 0; background-color:#ff2500;\">"
                        + testName + " Fail! " + platform + "</div></b>");
    }

    public void passedTest(String testName, String platform) {
        Reporter.log(
                "<br><b><div align=\"left\" style=\" height:30px; width:250px; padding: 10px;  border: 2px solid gray; margin: 0; background-color:#19FF3A;\">"
                        + testName + " OK! " + platform + "</div></b>");
    }

    public void clean(String dir) {
        File f = new File(dir);
        File[] files = f.listFiles();
        for (File file : files) {
            file.delete();
        }
    }
}
