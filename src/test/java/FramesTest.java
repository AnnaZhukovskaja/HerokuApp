import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class FramesTest {

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
    public void fileUpload() {
        driver.get("https://the-internet.herokuapp.com/frames");
        driver.findElement(By.cssSelector("[href='/iframe']")).click();
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        assertEquals(driver.findElement(By.xpath("//*[@id='tinymce']/p")).getText(),"Your content goes here.","You're not in IFrame.");
        driver.switchTo().defaultContent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

