import conf.ConfProperties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 *  Тестовый класс, который воспроизводит тесты на странице контакты
 */
public class ContactsPageTest {
    private WebDriver driver;
    private ContactsPage contactsPage;
    private ModumLabMainPage modumLabMainPage;

    private final String phoneNumberTextOne = "abc!@#";
    private final String phoneNumberTextTwo = "12345";
    private final String phoneNumberTextThree = "67890";
    private final String phoneNumberTextResult = phoneNumberTextTwo + phoneNumberTextThree;

    @Before
    public void setUp() {
        System.setProperty(ConfProperties.getProperty("driver"), ConfProperties.getProperty("driverpath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("contactspage"));
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
     *  Тест для проверки поля "номер телефона", что ввод любых данных кроме цифр недоступен. Проверяем, что
     *  сможем ввести все 10цифр телефона, поставив буквы и символы в начале
     */
    @Test
    public void checkInputPhoneNumberStart() {
        modumLabMainPage.clickNotificationClose();
        contactsPage.clickCallbackButton();
        contactsPage.sendNumberField(phoneNumberTextOne + phoneNumberTextTwo + phoneNumberTextThree);
        String numberField = contactsPage.getNumberField();
        numberField = numberField.substring(4).replace(")","")
                .replace(" ","").replace("-","");
        Assert.assertEquals(numberField, phoneNumberTextResult);
    }

    /**
     *  Тест для проверки поля "номер телефона", что ввод любых данных кроме цифр недоступен. Проверяем, что
     *  сможем ввести все 10цифр телефона, поставив буквы и символы в середине
     */
    @Test
    public void checkInputPhoneNumberMiddle() {
        modumLabMainPage.clickNotificationClose();
        contactsPage.clickCallbackButton();
        contactsPage.sendNumberField(phoneNumberTextTwo + phoneNumberTextOne + phoneNumberTextThree);
        String numberField = contactsPage.getNumberField();
        numberField = numberField.substring(4).replace(")","")
                .replace(" ","").replace("-","");
        Assert.assertEquals(numberField,phoneNumberTextResult);
    }

    /**
     *  Тест для проверки поля "номер телефона", что ввод любых данных кроме цифр недоступен. Проверяем, что
     *  сможем ввести все 10цифр телефона, поставив буквы и символы в конце
     */
    @Test
    public void checkInputPhoneNumberEnd() {
        modumLabMainPage.clickNotificationClose();
        contactsPage.clickCallbackButton();
        contactsPage.sendNumberField(phoneNumberTextTwo + phoneNumberTextThree + phoneNumberTextOne);
        String numberField = contactsPage.getNumberField();
        numberField = numberField.substring(4).replace(")","")
                .replace(" ","").replace("-","");
        Assert.assertEquals(numberField,phoneNumberTextResult);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

