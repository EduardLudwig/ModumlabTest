import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Класс для работы со страницей main
 *
 * @author Эдуард Людвиг
 */
public class ModumLabMainPage {
    private final WebDriver driver;

    public ModumLabMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By cookiesNotificationEnable = By.xpath("//div[@class='notification js-fixed-margin -active']");
    private final By notificationClose = By.xpath("//div[@id='notification']/a");

    /**
     * Метод, который совершает клик по закрытию окна оповещения о cookie
     */
    public void clickNotificationClose() {
        driver.findElement(notificationClose).click();
    }

    /**
     * Метод, который ищет окно уведомления о cookie
     *
     * @return try - если данное уведомление найдено
     * @return false - если данное уведомление не найдено
     */
    public boolean isDisplayedCookieNotification() {
        try {
            driver.findElement(cookiesNotificationEnable);
            return true;
        } catch (NoSuchElementException ignore) {
            return false;
        }
    }
}