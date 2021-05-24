import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Класс для работы со страницей contacts
 *
 * @author Эдуард Людвиг
 */
public class ContactsPage {

    private final WebDriver driver;
    private final Actions actions;

    private final By callbackButton = By.cssSelector(".-active .btn");
    private final By numberField = By.xpath("//div[@class='form__field']/input[@type='tel']");
    private final By callbackWindow = By.xpath("//div[@class='modal']/div[@class='modal__inner']");

    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        actions = (new Actions(driver));
    }

    /**
     *  Метод, который совершает клик по кнопке "Заказать звонок"
     */
    public void clickCallbackButton() {
        driver.findElement(callbackButton).click();
    }

    /**
     *  Метод, который вводит значение в поле телефонного номера
     *
     *  @param value - значение, которое принимает поле ввода телефонного номера
     */
    public void sendNumberField(String value) {
        driver.findElement(numberField).sendKeys(value);
    }

    /**
     *  Метод, который считывает значение поля телефонного номера
     *
     *  @return - значение из поля телефонного номера
     */
    public String getNumberField() {
        String arg = "value";
        return driver.findElement(numberField).getAttribute(arg);
    }

    /**
     *  Метод, который ищет модальное окно "Обратный звонок"
     *
     *  @return try - при его нахождении, false - при его отсутствии
     */
    public boolean checkCallbackWindow() {
        try {
            driver.findElement(callbackWindow);
            return true;
        } catch (NoSuchElementException ignore) {
            return false;
        }
    }

    /**
     *  Метод, который создает нам искуственное ожидание, для поиска отсутствуещего элемента
     *
     *  @return try - при его отсутствии, false - при его нахождении
     */
    public boolean isExistCallbackWindow() {
        for (int i = 0; i < 100; i++) {
            if (!checkCallbackWindow()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который совершает клик по указанным координатам
     *
     * @param x - принимает значение координаты x
     * @param y - принимает значение координаты y
     */
    public void clickOtherPosition(int x, int y) {
        actions.moveByOffset(x, y).click().perform();
    }
}

