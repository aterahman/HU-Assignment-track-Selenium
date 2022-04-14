import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class weathershopper
{
    WebDriver driver;

    //method to initilise WebDriver and the chrome driver
    @BeforeSuite
    public void setDriver()
    {
      System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
      driver = new ChromeDriver();
    }

    //method to open the url
    @BeforeClass
    public void openurl()
    {
        //opening the url
        driver.get("https://weathershopper.pythonanywhere.com/");

        //maximizing the window
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }

    
}
