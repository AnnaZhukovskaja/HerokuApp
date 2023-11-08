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

public class SortableDataTablesTest {

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
    public void sortableDataTables() {
        driver.get("https://the-internet.herokuapp.com/tables");
        Assert.assertEquals(driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]")).getText(),"Smith");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[2]/td[3]")).getText(),"fbach@yahoo.com");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[3]/td[4]")).getText(),"$100.00");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[4]/td[5]")).getText(),"http://www.timconway.com");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
