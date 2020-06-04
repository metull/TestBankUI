package Application;

import Common.Logs;
import Common.PageOperations;
import Test.UITest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;

public class ApplicationManager {
    public static RemoteWebDriver driver;
    public static ApplicationManager app;

    protected PageOperations page;
    protected Logs logs;
    protected UITest uiTest;

    public PageOperations page() {
        return page = new PageOperations(driver);
    }

    public Logs logs() {
        return logs = new Logs(driver);
    }

    public UITest uiTest() {
        return uiTest = new UITest(driver);
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

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    private static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public static void getInstance(String browserName) {
        app = new ApplicationManager();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = (RemoteWebDriver) createInstance(browserName, capabilities);
        setWebDriver(driver);
        getDriver().manage().window().maximize();
    }
}
