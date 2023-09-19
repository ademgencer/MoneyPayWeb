package stepdefs;

import driver.Driver;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import readers.PropertyReader;

public class HomePageTest extends BaseClass {
    // 1. https://moneyPAY.com.tr adresine git
    // 2. moneyPAY logosuna tıkla
    // 3. Ana sayfaya navigate olduğunu assert et
    // 4. Header menudeki ana sekmelere hover over yap
    // 5. Screen Capture al
    // 6. Çıkış yap

    @Test
    public void testHomePage(){

        HomePage homePage = new HomePage();

        driver.get(PropertyReader.read().get("url"));// 1.
        //Driver.getDriver(Browsers.edge).get("https://moneypay.com.tr");// 1. veya EDGE ile

        click$(homePage.moneyPayLogo);// 2.

        waitForVisibility(homePage.homePageText);// 3.

        homePage.moveToElements(homePage.headerMenuElements);// 4.

        getScreenshot("moneyPay_HomePage");// 5.

        Driver.quitDriver();

    }


}
