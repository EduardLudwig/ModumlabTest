import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 *
 * Class for
 * @author E. Ludwig
 *
 */
public class ModumLabMainPage {
    private WebDriver driver;

    public ModumLabMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By cookiesNotificationEnable = By.xpath("//div[@class='notification js-fixed-margin -active']");
    private By notificationClose = By.xpath("//div[@id='notification']/a");

    /**
     *
     */
    public void clickNotificationClose() {
        driver.findElement(notificationClose).click();
    }

    public boolean isDisplayedCookieNotification() {
        try {
            driver.findElement(cookiesNotificationEnable);
            return true;
        } catch (NoSuchElementException ignore) {
            return false;
        }
    }
}