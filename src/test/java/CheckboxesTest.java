import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckboxesTest {

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
    public void checkboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        Assert.assertFalse(driver.findElement(By.cssSelector("[type=checkbox]")).isSelected());
        driver.findElement(By.cssSelector("[type=checkbox]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[type=checkbox]")).isSelected());

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).isSelected());
        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).isSelected());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
