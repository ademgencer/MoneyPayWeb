package runners;

import driver.Browsers;
import driver.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


@CucumberOptions(
        // features dosyası java dosyası olmadığından java package ı dışında da çalışacağından path ini content root tan alırız.
        features = {"src/test/resources/features/"},// feature dosyasının source path ini yazıyoruz.
        // source dosyaları renklendirilmiştir projede, java lar gibi resources ler gibi...
        // glue java dosyası olduğundan java package ının altından itibaren tanımlanır ve çalışır, dışında açılmışsa çalışmaz zaten.
        glue = {"stepdefs"},// sadece çalışması istenen stepdefs in olduğu package ın path ini yazıyoruz.
        // Hooks bu package(glue) da degilse calistirilmaz.
        // glue = "{}" boş bırakılırsa tüm java packagelerı altına bakacaktır.
        tags = "",
        plugin = {"pretty", // Konsol da daha anlaşılır bir test çıktısı oluşturur.
                "json:test-output/cucumber-reports/cucumber.json", // cucumber json report
                "html:test-output/cucumber-reports/cucumber.html", // cucumber html report
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} // ExtentReport için gerekli ayarlamalar
                // runner dan senaryoyu çalıştırırsak plugin deki reportlar oluşturulur.
)
public class Runner extends AbstractTestNGCucumberTests {

    // TestNg class ıdır, aşağıya yazılan TestNG annotation lu
    // metotlar Cucumber metotlarından önce çalışır.
    // Xml file ile paralel test yapacaksak asagidaki gibi parametre girilir.

    /*
    @BeforeTest
    @Parameters("browser")// Parametre xml den gelir, eğer buradan çalıştırırsak parametre
    // gelmeyeceği için @Optional ile girilen default değer olan chrome çalışacaktır.
    public void beforeTest(@Optional("chrome") String browser) {

        // Daha teste başlamadan önce TestNG, drivera xml den parametre olarak girilen browser ı assign eder.
        Driver.getDriver(Browsers.valueOf(browser));

    }

     */

}
