import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.String;

//this is the base class for the tests
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
            new productlist();

            TimeUnit.MILLISECONDS.sleep(3000);
        }
        else
        {
            //skips the test
            throw new SkipException("Weather is under 34 degrees");
        }
    }

    @Test(priority = 5)
    public void storeproducts() {
        ArrayList<String> productnames = new ArrayList<>();

        int num = 0;

        for (int i = 2; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xlpath = "/html/body/div[1]/div[" + Integer.toString(i) + "]/div[" + Integer.toString(j) + "]/p[1]";
                WebElement productname = getelement(xlpath);
                productnames.add(productname.getText());
            }
        }
        System.out.println(productnames);
    }

    @Test(priority = 6)
    public void storeprices()
    {
        ArrayList<String> productprices = new ArrayList<>();

        for(int i=2;i<=3;i++)
        {
            for(int j = 1 ; j<=3;j++)
            {
                String xlpath = "/html/body/div[1]/div["+Integer.toString(i)+"]/div["+ Integer.toString(j)+"]/p[2]";
                WebElement productprice = getelement(xlpath);
                String p  = productprice.getText();
                String price  = "";
                for(int k = 0; k<p.length();k++)
                {
                    char ch = p.charAt(k);
                    if(Character.isDigit(ch))
                        price=price+ch;
                }
                productprices.add(price);

            }
        }
        System.out.println(productprices);
    }

    //method to add moisturizers to the cart
    @Test(priority = 7)
    public void addmoisturizer()throws InterruptedException
    {
        if(moisturizer)
        {
            //gets the element of i next to the "Moisturizer" title
            WebElement i = getelement("/html/body/div[1]/div[1]/span");

            //clicking on element
            i.click();

            TimeUnit.MILLISECONDS.sleep(3000);

            //adding least expensive aloe moisturizer to cart
            WebElement aloe = getelement("/html/body/div[1]/div[2]/div[3]/button");
            aloe.click();

            TimeUnit.MILLISECONDS.sleep(3000);

            //adding least expensive almond moisturizer to cart
            WebElement almond = getelement("/html/body/div[1]/div[2]/div[1]/button");
            almond.click();

            TimeUnit.MILLISECONDS.sleep(3000);

        }
        else
        {
            throw new SkipException("Temperature is over 19 degrees");
        }
    }

    //method to add sunscreens to the cart
    @Test(priority = 8)
    public void addsunscreen()throws InterruptedException
    {
        if(sunscreen)
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
        else
        {
            throw new SkipException("Weather is under 34 degrees");
        }
    }


    @Test(priority = 9)
    public void cartaccess()throws FileNotFoundException,InterruptedException
    {
        //getting the element of the cart button
        WebElement cartbutton = getelement("/html/body/nav/ul/button");

        //clicking on the button
        cartbutton.click();

        TimeUnit.MILLISECONDS.sleep(3000);

    }

    @Test(priority = 10)
    public void payment()
    {


        WebElement emailbox = getelement("/html/body/div[3]/form/div[2]/div/div[4]/div/div[1]/div/input");

        emailbox.sendKeys("abc@abc.com");


        Scanner ob = new Scanner("src//main//java//resources//carddetails.csv");
        String p ="";
        ob.useDelimiter(",");
        String cardnumber="";
        String month = "";
        String year = "";
        String cvv ="";
        while(ob.hasNext())
        {
            p = ob.next();
            String[] value = p.split(",");
            cardnumber = value[0];
            month = value[1];
            year = value[2];
            cvv = value[3];
        }

        System.out.println(cardnumber);

    }

    //method to take screenshots on passing
    public void passed()
    {
        File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(scrfile, new File("src\\Screenshots\\Passed"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //method to take screenshots on failure
    public void failed()
    {
        File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(scrfile, new File("src\\Screenshots\\Failed"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
