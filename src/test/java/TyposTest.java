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

public class TyposTest {

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
    public void typos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        for (int i = 0; i < 4; i++) {
            driver.navigate().refresh();
            Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]")).getAttribute("innerText"),
                    "Sometimes you'll see a typo, other times you won't.", "There are typo in the text.");
        }
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
