import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class weathershopper
{
    WebDriver driver;

    @BeforeSuite
    public void setDriver()
    {
      System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
      driver = new ChromeDriver();
    }

    @BeforeClass
    public void openurl
    {
        driver.get("https://weathershopper.pythonanywhere.com/");
    }
}
