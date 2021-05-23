import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Класс для работы со страницей contacts
 *
 * @author Эдуард Людвиг
 */
public class ContactsPage {

    private WebDriver driver;

    private final By callbackButton = By.cssSelector(".-active .btn");
    private final By numberField = By.xpath("//div[@class='form__field']/input[@type='tel']");
    private final By callbackWindow = By.xpath("//div[@class='modal']/div[@class='modal__inner']");
    private final By closeCallbackWindow = By.xpath("/html/body");
    private final By nonExists = By.xpath("non_exists-exist");
    private WebDriverWait wait;
    private Actions actions;

    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        wait = (new WebDriverWait(driver, 30));
        actions = (new Actions(driver));
    }

    public void clickCallbackButton() {
        driver.findElement(callbackButton).click();
    }

    public WebElement getCallbackButton() {
        return driver.findElement(callbackButton);
    }

    /**
     * Метод, который вводит значение в поле телефонного номера
     *
     * @param value - значение, которое принимает поле ввода телефонного номера
     */
    public void sendNumberField(String value) {
        driver.findElement(numberField).sendKeys(value);
    }

    /**
     * Метод, который считывает значение поля телефонного номера
     *
     * @return - значение из поля телефонного номера
     */
    public String getNumberField() {
        return driver.findElement(numberField).getAttribute("value");
    }

    public By getNonExists() {
        return nonExists;
    }

    public By getCallbackWindow() {
        return callbackWindow;
    }

    /**
     *
     */
    public boolean checkElement(By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException ignore) {
            return false;
        }
    }

    public void closeCallbackWindow() {
        driver.findElement(closeCallbackWindow).click();
    }

    public boolean isExistCallbackWindow(By element) {
        for (int i = 0; i < 10; i++) {
            if (checkElement(element)) {
                return true;
            }
        }
        return false;
    }
}

