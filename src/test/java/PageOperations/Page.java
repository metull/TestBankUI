package PageOperations;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Page {

    private int waitTime = 60;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, waitTime);
    }

    protected WebElement byXpath(String xpath, String description) {
        Reporter.log(" <br><b>" + description + " </b>");
        return wait.until(visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement byName(String xpath, String description) {
        Reporter.log(" <br><b>Вводим текст \"" + description + "\" </b>");
        return wait.until(visibilityOfElementLocated(By.name(xpath)));
    }

    // AJAX
    protected static ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {

                JavascriptExecutor jx = ((JavascriptExecutor) driver);
                JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
                WebElement element = driver.findElement(by);
                SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy_HH_mm_ss");
                String date = sdf.format(new Date());
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
                jsExecutor.executeScript("arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});", element);

                jx.executeScript("arguments[0].style.border='2px solid blue'", element);
                // Get element informations for log
                String el_text = element.toString();
                String elment_Info = el_text.substring(el_text.indexOf('-') + 1);
                Reporter.log("<br> </b><details><summary><b>" + elment_Info.substring(0, elment_Info.length() - 1)
                        + "</summary> </b><br>");

                // Create screenshot PNG
                File file = new File("");
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(scrFile, new File(file.getAbsolutePath() + "\\screenshots\\" + date + ".png"));
                    // Show image in report
                    Reporter.log(" <img src=..\\screenshots\\" + date + ".png" + " style=\"width:30%;\""
                            + "onclick=\"this.style.width='100%'\" " + "ondblclick=\"this.style.width='30%'\""
                            + " /> <br> </details></b>");
                    jx.executeScript("arguments[0].style.border='0px'", element);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return element.isDisplayed() ? element : null;
            }
        };
    }
}
