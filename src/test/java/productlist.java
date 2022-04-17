import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class productlist extends weathershopper {

    //first row names
    ///html/body/div[1]/div[2]/div[1]/p[1]
    ///html/body/div[1]/div[2]/div[2]/p[1]
    ///html/body/div[1]/div[2]/div[3]/p[1]

    //first row prices
    ///html/body/div[1]/div[2]/div[1]/p[2]
    ///html/body/div[1]/div[2]/div[2]/p[2]
    ///html/body/div[1]/div[2]/div[3]/p[2]

    //first row buttons
    ///html/body/div[1]/div[2]/div[1]/button
    ///html/body/div[1]/div[2]/div[2]/button
    ///html/body/div[1]/div[2]/div[3]/button

    //second row names
    ///html/body/div[1]/div[3]/div[1]/p[1]
    ///html/body/div[1]/div[3]/div[2]/p[1]
    ///html/body/div[1]/div[3]/div[3]/p[1]

    //second row prices
    ///html/body/div[1]/div[3]/div[1]/p[2]
    ///html/body/div[1]/div[3]/div[2]/p[2]
    ///html/body/div[1]/div[3]/div[3]/p[2]

    //second row buttons
    ///html/body/div[1]/div[3]/div[1]/button
    ///html/body/div[1]/div[3]/div[2]/button
    ///html/body/div[1]/div[3]/div[3]/button

    //storing all the details in a workbook


   public ArrayList<String> productnames = new ArrayList<>();
   public ArrayList<String> productprices = new ArrayList<>();
   public ArrayList<WebElement> buttons = new ArrayList<>();
   public ArrayList<String> aloe = new ArrayList<>();
   public ArrayList<String> almond = new ArrayList<>();
   public ArrayList<String> spf30 = new ArrayList<>();
   public ArrayList<String> spf50 = new ArrayList<>();
   public ArrayList<String> aloeprice = new ArrayList<>();
   public ArrayList<String> almondprice = new ArrayList<>();
   public ArrayList<String> spf30price = new ArrayList<>();
   public ArrayList<String> spf50price = new ArrayList<>();
   public ArrayList<WebElement> aloebuttons = new ArrayList<>();
   public ArrayList<WebElement> almondbuttons = new ArrayList<>();
   public ArrayList<WebElement> spf30buttons = new ArrayList<>();
   public ArrayList<WebElement> spf50buttons = new ArrayList<>();


    @Test(priority = 5)
    public void storeproducts() {

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
    public void storeprices() {
        for (int i = 2; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xlpath = "/html/body/div[1]/div[" + Integer.toString(i) + "]/div[" + Integer.toString(j) + "]/p[2]";
                WebElement productprice = getelement(xlpath);
                String p = productprice.getText();
                String price = "";
                for (int k = 0; k < p.length(); k++) {
                    char ch = p.charAt(k);
                    if (Character.isDigit(ch))
                        price = price + ch;
                }
                productprices.add(price);

            }
        }
        System.out.println(productprices);
    }

    @Test(priority = 7)
    public void storebuttons() {

        for (int i = 2; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xlpath = "/html/body/div[1]/div[" + Integer.toString(i) + "]/div[" + Integer.toString(j) + "]/button";
                WebElement productbutton = getelement(xlpath);
                buttons.add(productbutton);
            }
        }
        System.out.println(productnames);
    }

    @Test(priority = 8)
    public void sortproducts() {
        for (int i = 0; i < productnames.size(); i++) {
            String product = productnames.get(i);
            String price = productprices.get(i);
            WebElement button = buttons.get(i);
            if (product.contains("Aloe")) {
                aloe.add(product);
                aloeprice.add(price);
                aloebuttons.add(button);
            }
            else if (product.contains("Almond")) {
                almond.add(product);
                almondprice.add(price);
                almondbuttons.add(button);
            }
            else if (product.contains("SPF-30")) {
                spf30.add(product);
                spf30price.add(price);
                spf30buttons.add(button);
            }
           else if (product.contains("SPF-50")) {
                spf50.add(product);
                spf50price.add(price);
                spf50buttons.add(button);
            }

        }

    }

    @Test(priority = 9)
    public void comparemoisturizers() {
        if (moisturizer) {
            int leastaloeprice = 0;
            for (int i = 1; i < aloeprice.size(); i++) {
                int price = Integer.parseInt(aloeprice.get(leastaloeprice));
                int price1 = Integer.parseInt(aloeprice.get(i));
                if (price > price1) {
                    leastaloeprice = i;
                }
            }

            int leastalmondprice = 0;
            for (int i = 1; i < almondprice.size(); i++) {

                int price = Integer.parseInt(almondprice.get(leastalmondprice));
                int price1 = Integer.parseInt(almondprice.get(i));
                if (price > price1) {
                    leastalmondprice = i;
                }

            }
            aloebuttons.get(leastaloeprice).click();
            almondbuttons.get(leastalmondprice).click();

        } else {
            throw new SkipException("");
        }
    }

    @Test(priority = 10)
         public void comparesunscreens() {
        if (sunscreen) {
            int leastspf30price = 0;
            for (int i = 1; i < spf30price.size(); i++) {
                int price = Integer.parseInt(spf30price.get(leastspf30price));
                int price1 = Integer.parseInt(spf30price.get(i));
                if (price > price1) {
                    leastspf30price = i;
                }
            }

            int leastspf50price = 0;
            for (int i = 1; i < spf50price.size(); i++) {

                int price = Integer.parseInt(spf50price.get(leastspf50price));
                int price1 = Integer.parseInt(spf50price.get(i));
                if (price > price1) {
                    leastspf50price = i;
                }

            }
            spf30buttons.get(leastspf50price).click();
            spf50buttons.get(leastspf50price).click();

        }

    else
    {
        throw new SkipException("");
    }

    }
    }


