import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HomePageTest {
    private WebDriver driver;
    private ModumLabHomePage modumLabHomePage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pixifixi\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://modumlab.com");
        modumLabHomePage = new ModumLabHomePage(driver);
    }
    @Test
    public void cookieNotificationEnable(){
        modumLabHomePage.isDisplayedCookieNotification();
        driver.navigate().refresh();
        modumLabHomePage.isDisplayedCookieNotification();
    }
    @Test
    public void cookieNotificationDisable(){
        modumLabHomePage.isDisplayedCookieNotification();
        modumLabHomePage.clickNotificationClose();
        driver.navigate().refresh();
        Assert.assertFalse(modumLabHomePage.isDisplayedCookieNotification());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}