import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class weathershopper
{
    WebDriver driver;
    boolean moisturizer = false;
    boolean sunscreen = false;


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

    }

    //recurring method ot find the desired element by providing xpath
    public WebElement getelement(String xpath)
    {
        //find the element with the xpath provided
        WebElement element = driver.findElement(By.xpath(xpath));

        //returns element back to the method which called this method
        return element;
    }

    //method to get the temperature
    @Test(priority = 1)
    public int gettemperature()throws InterruptedException
    {
        //gets the element that displays the temperature on the screen
        WebElement temperature = getelement("/html/body/div/div[2]/div/span");

        TimeUnit.MILLISECONDS.sleep(3000);

        //gets the text of the element storing the temperature
        String temp = temperature.getText();

        //getting only the digits of the temperature stored in the string
        String temperaturedigits = "";
        for(int i=0; i<temp.length(); i++)
        {
            char c = temp.charAt(i);

            //checks whether the character is a digit
            if(Character.isDigit(c))
                temperaturedigits = temperaturedigits + c;
            else
                break;
        }

        int digitaltemperature = Integer.parseInt(temperaturedigits);
        return digitaltemperature;
    }

    //method to make decision based on temperature
    @Test(priority = 2)
    public void decision()throws InterruptedException
    {
        //gets the element of i next to the "Current Temperature" title
        WebElement i = getelement("/html/body/div/div[1]/span");

        //clicks on the element
        i.click();

        TimeUnit.MILLISECONDS.sleep(3000);

        int temperature = gettemperature();

        //making decision as per the information box
        if(temperature<19)
            moisturizer = true;
        else if(temperature>34)
            sunscreen = true;
        else {
            driver.navigate().refresh();
            gettemperature();
            decision();
        }

    }

    @Test(priority = 3)
    public void buymoisturizer()throws InterruptedException
    {
        if(moisturizer) {
            //getting the buy moisturizer button
            WebElement buymoisturizers = getelement("/html/body/div[1]/div[3]/div[1]/a/button");

            //clicking on the button
            buymoisturizers.click();

            TimeUnit.MILLISECONDS.sleep(3000);
        }
        else
        {
            //skips the test
            throw new SkipException("Weather is over 19 degrees");
        }

    }



    //method to go to the sunscreen page
    @Test(priority = 4)
    public void buysunscreen()throws InterruptedException
    {
        if(sunscreen)
        {
            //getting the buy sunscreens button
            WebElement buysunscreens = getelement("/html/body/div[1]/div[3]/div[2]/a/button");

            //clicking on the button
            buysunscreens.click();

            TimeUnit.MILLISECONDS.sleep(3000);
        }
        else
        {
            //skips the test
            throw new SkipException("Weather is under 19 degrees");
        }
    }

    //method to add sunscreens to the cart
    @Test(priority = 5)
    public void addsunscreen()throws InterruptedException
    {
        //gets the element of i next to the "Sunscreen" title"
        WebElement i = getelement("/html/body/div[1]/div[1]/span");

        //clicks on element
        i.click();

        TimeUnit.MILLISECONDS.sleep(3000);

        //adding least expensive spf 50 sunscreen
        WebElement spf50 = getelement("/html/body/div[1]/div[3]/div[1]/button");
        spf50.click();

        TimeUnit.MILLISECONDS.sleep(3000);

        //adding least expensive spf 30 sunscreen
        WebElement spf30 = getelement("/html/body/div[1]/div[2]/div[3]/button");
        spf30.click();

        TimeUnit.MILLISECONDS.sleep(3000);

    }


    //method to close the driver
    @AfterClass
    public void close()
    {
        //closes the window
        driver.close();

        //closes the driver
        driver.quit();
    }
}
