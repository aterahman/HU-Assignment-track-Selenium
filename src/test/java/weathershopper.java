import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.String;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

@Listeners(testnglistener.class)
//this is the base class for the tests
public class weathershopper
{
    WebDriver driver;
    boolean moisturizer = false;
    boolean sunscreen = false;
    public static final Logger log = LogManager.getLogger(weathershopper.class);
    String leastexpensiveproduct1 = "";
    String leastexpensiveproduct2 = "";


    //method to initilise WebDriver and the chrome driver
    @BeforeSuite
    public void setDriver()
    {
      System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
      driver = new ChromeDriver();
      log.info("Driver is being setup");
    }

    //method to open the url
    @BeforeClass
    public void openurl()
    {
        //opening the url
        driver.get("https://weathershopper.pythonanywhere.com/");

        //maximizing the window
        driver.manage().window().maximize();
        log.info("Url is Open");

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
        log.info("The Temperature is:"+temp);
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
        log.info("Decision has been made as per the temperature");

    }

    @Test(priority = 3)
    public void buymoisturizer()throws InterruptedException
    {
        if(moisturizer)
        {
            log.info("As per the temperature we will be buying Moisturizers");

            //getting the buy moisturizer button
            WebElement buymoisturizers = getelement("/html/body/div[1]/div[3]/div[1]/a/button");

            //clicking on the button
            buymoisturizers.click();

            log.info("Buy moisturizers button has been clicked");

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
            log.info("As per the temperature we will be buying Sunscreens");

            //getting the buy sunscreens button
            WebElement buysunscreens = getelement("/html/body/div[1]/div[3]/div[2]/a/button");

            //clicking on the button
            buysunscreens.click();

            log.info("Buy sunscreens button has been clicked");

            TimeUnit.MILLISECONDS.sleep(3000);

        }
        else
        {
            //skips the test
            throw new SkipException("Weather is under 34 degrees");
        }
    }


    @Test(priority = 11)
    public void cartaccess()throws FileNotFoundException,InterruptedException
    {
        //getting the element of the cart button
        WebElement cartbutton = getelement("/html/body/nav/ul/button");

        //clicking on the button
        cartbutton.click();
        log.info("Cart button has been clicked");

        TimeUnit.MILLISECONDS.sleep(3000);

        WebElement cartitem1 = getelement("/html/body/div[1]/div[2]/table/tbody/tr[1]/td[1]");
        String product1 = cartitem1.getText();
        System.out.println(product1);

        WebElement cartitem2 = getelement("/html/body/div[1]/div[2]/table/tbody/tr[2]/td[1]");
        String product2 = cartitem2.getText();
        System.out.println(product2);

        System.out.println(leastexpensiveproduct1);
        System.out.println(leastexpensiveproduct2);
        Assert.assertTrue(product1.equals(leastexpensiveproduct1) && product2.equals(leastexpensiveproduct2));
    }

    String cardnumber1="";
    String cardnumber2="";
    String cardnumber3="";
    String cardnumber4="";
    String month = "";
    String year = "";
    String cvv ="";
    String zipcode = "";

    @Test(priority = 12)
    public void excel()throws FileNotFoundException, IOException {
        String path = "C:\\Users\\aterahman\\HU_assignment_Track_Selenium\\src\\main\\resources\\carddetails.csv";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        while ((line = br.readLine()) != null)
        {
            String[] values = line.split(",");
            cardnumber1 = values[0];
            cardnumber2 = values[1];
            cardnumber3 = values[2];
            cardnumber4 = values[3];
            month = values[4];
            year  = values[5];
            cvv = values[6];
            zipcode=values[7];

        }
    }


    //method to enter payment details and do the payment
    @Test(priority = 13)
    public void payment()throws InterruptedException,FileNotFoundException, IOException
    {
        WebElement paywithcard = getelement("/html/body/div[1]/div[3]/form/button");

        paywithcard.click();

        TimeUnit.MILLISECONDS.sleep(3000);

        driver.switchTo().frame("stripe_checkout_app");

        TimeUnit.MILLISECONDS.sleep(1000);

        WebElement emailbox = getelement("/html/body/div[3]/form/div[2]/div/div[4]/div/div[1]/div/input");

        emailbox.sendKeys("abc@abc.com");

        WebElement cardnumberinput = getelement("/html/body/div[3]/form/div[2]/div/div[4]/div/div[2]/div/div/div/div/div/div/div[1]/input");
        cardnumberinput.sendKeys(cardnumber1);
        cardnumberinput.sendKeys(cardnumber2);
        cardnumberinput.sendKeys(cardnumber3);
        cardnumberinput.sendKeys(cardnumber4);

        TimeUnit.MILLISECONDS.sleep(1000);

        WebElement date = getelement("/html/body/div[3]/form/div[2]/div/div[4]/div/div[2]/div/div/div/div/div/div/div[2]/input");
        date.sendKeys(month);
        date.sendKeys(year);

        TimeUnit.MILLISECONDS.sleep(1000);

        WebElement cvvinput = getelement("/html/body/div[3]/form/div[2]/div/div[4]/div/div[2]/div/div/div/div/div/div/div[3]/input");
        cvvinput.sendKeys(cvv);

        TimeUnit.MILLISECONDS.sleep(1000);

        WebElement zip = getelement("/html/body/div[3]/form/div[2]/div/div[4]/div/div[2]/div/div/div/div/div/div/div[4]/input");
        zip.sendKeys(zipcode);

        WebElement paybutton = getelement("/html/body/div[3]/form/div[2]/div/div[3]/div/div/div/button/span/span");
        paybutton.click();

        TimeUnit.MILLISECONDS.sleep(5000);

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
