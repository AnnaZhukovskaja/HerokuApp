import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HoversTest {

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
    public void howers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        Actions action = new Actions(driver);
        WebElement webElementUserOne = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
        action.moveToElement(webElementUserOne).build().perform();
        Assert.assertEquals(driver.findElement(By.tagName("h5")).getText(),"name: user1");
        action.moveToElement(webElementUserOne).moveToElement(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"))).click().build().perform();
        Assert.assertNotEquals(driver.findElement(By.tagName("h1")),"Not Found");

        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement webElementUserTwo = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/img"));
        action.moveToElement(webElementUserTwo).build().perform();
        Assert.assertEquals(driver.findElement(By.tagName("h5")).getText(),"name: user2");
        action.moveToElement(webElementUserTwo).moveToElement(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/a"))).click().build().perform();
        Assert.assertNotEquals(driver.findElement(By.tagName("h1")),"Not Found");

        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement webElementUserThree = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/img"));
        action.moveToElement(webElementUserThree).build().perform();
        Assert.assertEquals(driver.findElement(By.tagName("h5")).getText(),"name: user3");
        action.moveToElement(webElementUserThree).moveToElement(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/a"))).click().build().perform();
        Assert.assertNotEquals(driver.findElement(By.tagName("h1")),"Not Found");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
