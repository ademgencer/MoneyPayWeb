package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.HomePage;
import readers.PropertyReader;

import java.util.List;

public class MyStepdefs extends BaseClass {
    HomePage homePage = new HomePage();

    @Given("Kullanici ana sayfaya girer")
    public void kullaniciAnaSayfayaGirer() {
        driver.get(PropertyReader.read().get("url"));
    }

    @When("monayPay logosuna click yapar")
    public void monaypayLogosunaClickYapar() {
        click$(homePage.moneyPayLogo);
    }

    @Then("Ana sayfaya navigate olmalidir")
    public void anaSayfayaNavigateOlmalidir() {
        waitForVisibility(homePage.homePageText);
    }

    @When("Kullanici header menu ve altındaki submenu lere hover over yapar")
    public void kullaniciHeaderMenudekiSekmelereHoverYapar() {
        for (WebElement headerMenuElement : homePage.headerMenuElements) {
            moveToElement(headerMenuElement);
            waitByActions(1000);// Aksiyonları takip için yazdım, silinebilir.
            if (homePage.headerSubMenuElements.size() > 0) {
                List<WebElement> subElements = headerMenuElement.findElements(By.xpath(".//li"));
                moveToElements$(subElements);
            }
        }
    }

    @Then("Hover over yapılabilmelidir")
    public void hoverOverYapılabilmelidir() {
        // Assertion lar, action step inde yapıldı.
    }
}
