package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driver.DriverFactory.*;

public class Driver {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();// Birden fazla threat te çalışabilecek driver.

    private static ThreadLocal<WebDriverWait> waits = new ThreadLocal<>();// Listedir ama her threat için 1 elemanlıdır, sadece set ve get metotu vardır.

    private static ThreadLocal<Browsers> browsers = new ThreadLocal<>();// Birden fazla threat te çalışabilecek browser.

    public static WebDriver getDriver() {

        if (browsers.get() == null)// İlk kez browser atanıyorsa default olarak Chrome atayacak, fakat aynı thread deki testte
            // senaryo başlangıcında browser assign edilmiş ise quit ten sonra driver null olsa bile burada browser null olmayarak
            // default chrome browser ataması yapılmaz ve önceki browser ne ise onu return eder.

            browsers.set(Browsers.firefox);

        return getDriver(browsers.get());// Fakat öncesinde browser a bir değer atanmışsa
        // (xml den parametre girilerek mesela) yeniden browser ataması yapmadan önceden
        // atanmış browser ı kullanmaya devam eder.

    }

    public static WebDriver getDriver(Browsers browser) {
        // if (browsers.get() == null)// Bu if bloğunu aktif edersek her senaryo sonrası browser değişebilir.
        browsers.set(browser);// Senaryoya başlarken ilk atanan driver ın yedeği olarak browsers a kaydediyoruz.
        // Cucumber @After da Driver.quitDriver() olduğundan cucumber ile bir thread de test devam ederken her
        // senaryo sonrası farklı bir browser run edilebilir. Yani Edge ile test yapcağız fakat quit ten sonra
        // driver null ayarlanır, diğer senaryoya geçerken her seferinde xml den yeniden edge browser parametresi
        // girilemediği için default driver olan Chrome a geçiyor mesela.(Scenario Outline her seferinde yeni bir senaryo gibi çalışır)
        // Eğer senaryo btiminde quitDriver() yapılmamış ise driver()==null olmadığı için driver değişmez tabii.

        if (drivers.get() == null) {// driver null ise sıfırdan driver oluşturur, ama öncesinde atanmış bir driver
            // varsa yeniden driver ataması yapmaz ve onu kullanmaya devam eder.
            switch (browser) {
                case firefox:
                    drivers.set(createFirefox());// Burada kalabalık olmaması için DriverFactory Classında tanımladık.
                    break;
                case edge:
                    drivers.set(createEdge());
                    break;
                case ie:
                    drivers.set(createIe());
                    break;
                case safari:
                    drivers.set(createSafari());
                    break;
                default:
                    drivers.set(createChrome());
                    break;
            }
        }

        waits.set(new WebDriverWait(drivers.get(), Duration.ofSeconds(10)));// waits i set etmeliyiz.

        return drivers.get();
    }

    public static WebDriverWait getWait() {
        return waits.get();// listenin tek elemanı vardır ve onu getirir. index yazmaya gerek yoktur.
    }

    public static void quitDriver() {
        if (drivers.get() != null) {
            drivers.get().quit();
            drivers.set(null);// çıkış yaptıktan sonra null yapıyoruz ki tekrar driver
            // kullanılacaksa istenen driver yeniden atanabilsin, driver türü değiştirilebilsin.
            waits.set(null);
        }
    }

}

