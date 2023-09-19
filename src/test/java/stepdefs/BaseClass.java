package stepdefs;

import driver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BaseClass {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected BaseClass() {
        driver = Driver.getDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(100));// wait e driver verdim.
        wait = Driver.getWait();// Yeni wait budur. Herkes ortak ortak wait i kullanır böylece, ortak driver gibi...
    }

    public void click$(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        click$(element);
    }

    /**
     * Bu metot element e sırasıyla Selenium, Actions ve JS ile click etmeyi dener.
     *
     * @param element WebElement
     */
    public BaseClass click$(WebElement element) {// totalde 100 ms aralıklarla 10 sn tıklamayı dener, bu metot çalışır.
        wait.until(driver1 -> {// Aslında yukarıda wait e verilen driverla aynıdır. Lambda metodu kullandık. Istersek
            // driver1 yerine "e" ya da istenen değişken adı yazılabilir. list.forEach(e-> sout(e.getText)) kullanımı gibi.
            // Lambda da -> { } kullanılırsa bir değer return etmek zorundadır.
            try {
                element.click();// önce elemente selenium ile click etmeyi dener.
                return true;
            } catch (Exception e) {
                try {// selenium tıklayamazsa Actions Class tan actions la click deneyelim.
                    new Actions(driver1).moveToElement(element).click().perform();
                    return true;
                } catch (Exception e2) {
                    try {// actions da tıklayamazsa en son JS ile click deneyelim.
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].click();", element);
                        return true;
                    } catch (Exception e3) {
                        return false;
                    }
                }

            }
        });
        return this;
    }

    public void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        sendKeys(element, text);
    }

    /**
     * Bu metot element e sırasıyla Selenium, Actions ve JS ile sendKeys etmeyi dener.
     *
     * @param element WebElement
     * @param text    String
     */
    public void sendKeys(WebElement element, String text) {
        wait.until(driver1 -> {// Aslında yukarıda wait e verilen driverla aynıdır. Lambda metodu kullandık.
            try {
                element.clear();
                element.sendKeys(text);
                return true;
            } catch (Exception e) {
                try {// selenium tıklayamazsa action la deneyelim.
                    element.clear();
                    new Actions(driver1).moveToElement(element).sendKeys(text).perform();
                    return true;
                } catch (Exception e2) {
                    try {// action da tıklayamazsa JS ile deneyelim.
                        element.clear();
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].value=" + text, element);
                        return true;
                    } catch (Exception e3) {// Hiç biriyle totalde 10 sn boyunca send edemezsek hata verelim.
                        return false;
                    }
                }

            }
        });
    }

    public void waitForVisibility(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        waitForVisibility(element);
    }

    /**
     * Bu metot element visible olana dek bekler.
     *
     * @param element WebElement
     */
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void getScreenshot(String name) {

        String isim = "C:/Users/work/Desktop/Emek Projeleri/MoneyPAY/screenShots/" + name + "_" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".png";

        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File target = new File(isim);

        try {
            FileUtils.copyFile(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void moveToElement(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

        (new Actions(driver)).moveToElement(element).perform();

    }

    /**
     * Listedeki elementlere hover yapar.
     *
     * @param elements
     */
    public BaseClass moveToElements$(List<WebElement> elements) {
        elements.forEach(element -> {
            (new Actions(driver)).moveToElement(element).perform();
        });
        return this;
    }

    public WebElement getWebElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitByActions(long millis){
        new Actions(driver).pause(millis).perform();
    }

}
