import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 *  Тестовый класс, который воспроизводит тесты на странице контакты
 */
public class ContactsPageTest {
    private WebDriver driver;
    private ContactsPage contactsPage;
    private ModumLabMainPage modumLabMainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pixifixi\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://modumlab.com/contacts#spb");
        contactsPage = new ContactsPage(driver);
        modumLabMainPage = new ModumLabMainPage(driver);
    }

    /**
     *  Тест для проверки, что после нажатия кнопки "заказать звонок", появляется форма ввиде перекрывающего окна
     */
    @Test
    public void checkCallbackWindow() {
        modumLabMainPage.clickNotificationClose();
        contactsPage.clickCallbackButton();
        contactsPage.checkCallbackWindow();
    }

    /**
     *  Тест для проверки скрытия формы обратной звонок, при нажатие вне окна формы
     */
    @Test
    public void checkCloseCallbackForm(){
        modumLabMainPage.clickNotificationClose();
        contactsPage.clickCallbackButton();
        Assert.assertTrue(contactsPage.checkCallbackWindow());
        contactsPage.clickOtherPosition(0, driver.manage().window().getSize().getHeight()/2);
        Assert.assertTrue(contactsPage.isExistCallbackWindow());
        Assert.assertFalse(contactsPage.checkCallbackWindow());
    }

    /**
     *
     */
    @Test
    public void checkInputPhoneNumber() {
        modumLabMainPage.clickNotificationClose();
        contactsPage.clickCallbackButton();
        contactsPage.sendNumberField("123!#*asd");
        String numberField = contactsPage.getNumberField();
        numberField = numberField.substring(4).replace(")","")
                .replace(" ","");
        Assert.assertEquals(numberField,"123");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

