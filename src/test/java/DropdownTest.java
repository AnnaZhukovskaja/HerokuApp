import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropdownTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void dropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement drowndown = driver.findElement(By.id("dropdown"));
        Select select = new Select(drowndown);
        List<WebElement> options = select.getOptions();
        Assert.assertEquals(options.get(0).getText(),"Please select an option");
        Assert.assertEquals(options.get(1).getText(),"Option 1");
        Assert.assertEquals(options.get(2).getText(),"Option 2");
        drowndown.click();
        select.selectByVisibleText("Option 1");
        Assert.assertTrue(select.getFirstSelectedOption().isSelected(), "Option 1 not selected");
        select.selectByVisibleText("Option 2");
        Assert.assertTrue(select.getFirstSelectedOption().isSelected(), "Option 2 not selected");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
