import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InputsTest {

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
    public void inputs() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.findElement(By.cssSelector("[type=number]")).sendKeys("letters");
        Assert.assertEquals(driver.findElement(By.cssSelector("[type=number]")).getAttribute("value"),"");
        driver.findElement(By.cssSelector("[type=number]")).sendKeys("5");
        driver.findElement(By.cssSelector("[type=number]")).sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(driver.findElement(By.cssSelector("[type=number]")).getAttribute("value"),"6");
        driver.findElement(By.cssSelector("[type=number]")).sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(driver.findElement(By.cssSelector("[type=number]")).getAttribute("value"),"5");
        driver.findElement(By.cssSelector("[type=number]")).clear();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
