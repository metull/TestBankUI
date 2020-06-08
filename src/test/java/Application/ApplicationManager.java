package Application;

import Common.Logs;
import PageOperations.BankOpenPage;
import PageOperations.GooglePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class ApplicationManager {
    public static RemoteWebDriver driver;
    public static ApplicationManager app;

    protected GooglePage googlePage;
    protected BankOpenPage bankOpenPage;
    protected Logs logs;

    public GooglePage googlePage() {
        return googlePage = new GooglePage(driver);
    }

    public BankOpenPage bankOpenPage() {
        return bankOpenPage = new BankOpenPage(driver);
    }

    public Logs logs() {
        return logs = new Logs(driver);
    }

    private static WebDriver createInstance(String browserName, DesiredCapabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        File DriverPath = new File("");
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("chrome")) {
            System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
            System.setProperty("USER_AGENT", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
            System.setProperty("webdriver.chrome.driver", DriverPath.getAbsolutePath() + "\\drivers\\chromedriver.exe");
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public static void cleanDir(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    cleanDir(dir);
                } else {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    private static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void getInstance(String browserName) {
        app = new ApplicationManager();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = (RemoteWebDriver) createInstance(browserName, capabilities);
        setWebDriver(driver);
        getDriver().manage().window().maximize();
    }
}
