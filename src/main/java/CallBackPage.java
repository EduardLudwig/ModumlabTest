import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 *
 * Class for
 * @author E. Ludwig
 */
public class CallBackPage {

    private WebDriver driver;

    private final By callbackButton = By.cssSelector(".-active .btn");
    private final By numberField = By.xpath("//div[@class='form__field']/input[@type='tel']");
    private final By callbackWindow = By.xpath("//div[@class='modal__inner']");
    private final By closeCallbackWindow = By.xpath("//div[@class='modal__wrapper']");

    public CallBackPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCallbackButton() {
        driver.findElement(callbackButton).click();
    }

    /**
     *
     * @param value
     */
    public void sendNumberField(String value) {
        driver.findElement(numberField).sendKeys(value );
    }

    /**
     *
     * @return
     */
    public String getNumberField() {
        return driver.findElement(numberField).getAttribute("value");
    }

    public void checkCallbackWindow(){
        driver.findElement(callbackWindow);
    }

    public void closeCallbackWindow(){
        driver.findElement(closeCallbackWindow).click();
    }
}

