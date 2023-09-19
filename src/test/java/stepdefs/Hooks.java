package stepdefs;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import readers.PropertyReader;

public class Hooks {

    // Cucumber notasyonları, varsa TestNG notasyonlarından sonra çalışırlar.
    // Hooks dosyası stepdefs package ında değilse cucumber çalıştırılmaz çünkü TestNG runner Classından
    // @CucumberOptions notasyonu sebebiyle cucumber ı çalıştırır fakat Cucumber notasyonlu metotlar Hooks
    // içinde olduğundan glue da değilse ya da Hooks farklı package da  ise package ı glue da ayrıca
    // yazılmamışsa çalıştıramayacaktır.

    @After(order = 1)// Senaryonun run edilmesi bitince çalışır.
    public void quit() {// Her senaryodan sonra çalışır.
        Driver.quitDriver();
    }

    @After()// After her senaryo sonrası çalışır. -> AfterStep dersek her step sonu çalışır.
    public void after1(Scenario scenario) {
        boolean screenShot = PropertyReader.read().get("takescreenshot").equalsIgnoreCase("true");// true, false
        boolean screenShotOn = PropertyReader.read().get("takescreenshot.on").equalsIgnoreCase("fail");// fail, all
        if (screenShot) {// Feature daki bir Senaryodaki herhangi bir step fail olursa senaryo biter ve devreye girer.
            if (screenShotOn) {// step step SS alınacaksa bu bloğa girer -> tabi önce AfterStep diyerek bu metotu step step çalıştırmalıyız.
                if (scenario.isFailed()) {// Senaryo fail ise bu bloğa girer.
                    byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                    // ekran fotosunu file değilde byte olarak(0 ve 1 ler gibi sayısal olark) tutar.
                    scenario.attach(screenshot, "image/png", scenario.getName());
                    // senaryomuza attach(ek) ediyoruz; screenshot, media tip ve senaryo adını giriyoruz.
                    // Bu ekran fotosunu raporda görebileceğiz. Ayrıca test-output/screenshots/ altına kaydedilir.
                    // Bu kayıt adresi extent.properties de verilmiştir.
                }
            } else {// step step çekilmeyecekse bu bloğa girer, senaryo bitimi SS alır.
                byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }

        }
    }


}
