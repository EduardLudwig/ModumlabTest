import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


/**
 *  Тестовый класс, который воспроизводит тесты на главной странице
 */
public class MainPageTest {
    private WebDriver driver;
    private ModumLabMainPage modumLabMainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pixifixi\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://modumlab.com");
        modumLabMainPage = new ModumLabMainPage(driver);
    }

    /**
     *  Тест для проверки наличия уведомления о куки
     */
    @Test
    public void cookieNotification(){
        modumLabMainPage.isDisplayedCookieNotification();
    }

    /**
     *  Тест для проверки возможности закрытия уведомления о куки кнопкой-крестик
     */
    @Test
    public void cookieNotificationClose(){
       modumLabMainPage.isDisplayedCookieNotification();
       modumLabMainPage.clickNotificationClose();
       Assert.assertFalse(modumLabMainPage.isDisplayedCookieNotification());
    }

    /**
     *  Тест для проверки появление уведомления о куки, при повторном посещения сайта без сокрытия данного уведомления
     *  при первом посещение
     */
    @Test
    public void cookieNotificationEnable(){
        modumLabMainPage.isDisplayedCookieNotification();
        driver.navigate().refresh();
        modumLabMainPage.isDisplayedCookieNotification();
    }

    /**
     *  Тест для проверки не появление уведомления о куки, при повторном посещения сайта, после его сокрытия
     */
    @Test
    public void cookieNotificationDisable(){
        modumLabMainPage.isDisplayedCookieNotification();
        modumLabMainPage.clickNotificationClose();
        driver.navigate().refresh();

        Assert.assertFalse(modumLabMainPage.isDisplayedCookieNotification());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}