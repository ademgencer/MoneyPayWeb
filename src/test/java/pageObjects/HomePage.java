package pageObjects;

import driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepdefs.BaseClass;

import java.util.List;

public class HomePage extends BaseClass {// HomePage nesnelerini(WebElement) bu Class ta topladık.
    // Bunlara ulaşmak için bu Classın bir örneğini oluşturarak elenmentlere erişeceğiz.

    public HomePage() {// Bu sayfanın constructer ıdır.
        PageFactory.initElements(Driver.getDriver(),this);
        // Bu sayfadaki(this) WebElementleri initialize(başlatıyoruz) ediyoruz. Yoksa çalışmazlar.
    }

    @FindBy(xpath = "//a[./img[@src='assets/images/logo.png']]")
    public WebElement moneyPayLogo;

    @FindBy(xpath = "//h1[contains(.,'Tüm ihtiyaçların için tek bir hesap')]")
    public WebElement homePageText;

    @FindBy(xpath = "//div[@class='menu']/ul/li")
    public List<WebElement> headerMenuElements;

    @FindBy(xpath = "//div[@class='menu']/ul/li//li")
    public List<WebElement> headerSubMenuElements;

    /**
     * Listedeki elementlere hover yapar.
     * @param elements
     */
    public void moveToElements(List<WebElement> elements){
        elements.forEach(element -> {
            (new Actions(driver)).moveToElement(element).perform();
        });

    }


}
