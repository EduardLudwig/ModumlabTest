import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CallBackPageTest {
    private WebDriver driver;
    private CallBackPage callBackPage;
    private ModumLabHomePage modumLabHomePage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pixifixi\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://modumlab.com/contacts#spb");
        callBackPage = new CallBackPage(driver);
        modumLabHomePage = new ModumLabHomePage(driver);
    }

    /**
     *
     */
    @Test
    public void checkCallbackForm(){
        modumLabHomePage.clickNotificationClose();
        callBackPage.clickCallbackButton();
        callBackPage.checkCallbackWindow();
        callBackPage.closeCallbackWindow();
    }

    /**
     *
     */
    @Test
    public void checkInputPhoneNumber() {
        modumLabHomePage.clickNotificationClose();
        callBackPage.clickCallbackButton();
        callBackPage.sendNumberField("123!#*asd");
        String numberField = callBackPage.getNumberField();
        numberField = numberField.substring(4).replace(")","")
                .replace(" ","");
        Assert.assertEquals(numberField,"123");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

