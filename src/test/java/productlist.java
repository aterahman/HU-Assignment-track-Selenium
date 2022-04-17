import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.ArrayList;

//extended class to store and compare product prices and select the least expensive ones
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


   //method to store product names into a single array list
    @Test(priority = 5)
    public void storeproducts() {

        for (int i = 2; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xlpath = "/html/body/div[1]/div[" + Integer.toString(i) + "]/div[" + Integer.toString(j) + "]/p[1]";
                WebElement productname = getelement(xlpath);
                productnames.add(productname.getText());
            }
        }
        log.info("The products on the screen are "+productnames);
    }

    //method to store product prices into a single array list
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
        log.info("The prices of the prducts are "+productprices);
    }

    //method to store the buttons of the products into a single array list
    @Test(priority = 7)
    public void storebuttons() {

        for (int i = 2; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                String xlpath = "/html/body/div[1]/div[" + Integer.toString(i) + "]/div[" + Integer.toString(j) + "]/button";
                WebElement productbutton = getelement(xlpath);
                buttons.add(productbutton);
            }
        }
    }

    //method to split products into different array lists based on their type
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

    //method to find the least expensive aloe and almond moisturizers and add them to cart
    @Test(priority = 9)
    public void comparemoisturizers() {
        if (moisturizer) {
            log.info("Aloe products are: "+aloe);
            log.info("Aloe product prices are: "+aloeprice);
            int leastaloeprice = 0;
            for (int i = 1; i < aloeprice.size(); i++) {
                int price = Integer.parseInt(aloeprice.get(leastaloeprice));
                int price1 = Integer.parseInt(aloeprice.get(i));
                if (price > price1) {
                    leastaloeprice = i;
                }
            }
            log.info("Almond products are: "+almond);
            log.info("Almond product prices are: "+almondprice);
            int leastalmondprice = 0;
            for (int i = 1; i < almondprice.size(); i++) {

                int price = Integer.parseInt(almondprice.get(leastalmondprice));
                int price1 = Integer.parseInt(almondprice.get(i));
                if (price > price1) {
                    leastalmondprice = i;
                }

            }

            log.info("Least expensive aloe product is : "+aloe.get(leastaloeprice) +" at Rs"+aloeprice.get(leastaloeprice));
            log.info("It has been added to cart");
            aloebuttons.get(leastaloeprice).click();

            log.info("Least expensive almond product is : "+almond.get(leastalmondprice) +" at Rs"+almondprice.get(leastalmondprice));
            log.info("It has been added to cart");
            almondbuttons.get(leastalmondprice).click();

            leastexpensiveproduct1=aloe.get(leastaloeprice);
            leastexpensiveproduct2=almond.get(leastalmondprice);

            System.out.println(leastexpensiveproduct1);
            System.out.println(leastexpensiveproduct2);

        } else {
            throw new SkipException("");
        }
    }

    //method to find the least expensive spf30 and spf50 sunscreens and add them to cart
    @Test(priority = 10)
         public void comparesunscreens() {
        if (sunscreen) {
            log.info("SPF 30 products are: "+spf30);
            log.info("SPF 30 product prices are: "+spf30price);
            int leastspf30price = 0;
            for (int i = 1; i < spf30price.size(); i++) {
                int price = Integer.parseInt(spf30price.get(leastspf30price));
                int price1 = Integer.parseInt(spf30price.get(i));
                if (price > price1) {
                    leastspf30price = i;
                }
            }

            log.info("SPF 50 products are: "+spf50);
            log.info("SPF 50 product prices are: "+spf50price);
            int leastspf50price = 0;
            for (int i = 1; i < spf50price.size(); i++) {

                int price = Integer.parseInt(spf50price.get(leastspf50price));
                int price1 = Integer.parseInt(spf50price.get(i));
                if (price > price1) {
                    leastspf50price = i;
                }

            }

            log.info("Least expensive SPF30 product is : "+spf30.get(leastspf30price) +" at Rs"+spf30price.get(leastspf30price));
            log.info("It has been added to cart");
            spf30buttons.get(leastspf30price).click();

            log.info("Least expensive SPF50 product is : "+spf50.get(leastspf50price) +" at Rs"+spf50price.get(leastspf50price));
            log.info("It has been added to cart");
            spf50buttons.get(leastspf50price).click();

            leastexpensiveproduct1=spf30.get(leastspf30price);
            leastexpensiveproduct2=spf50.get(leastspf50price);

            System.out.println(leastexpensiveproduct1);
            System.out.println(leastexpensiveproduct2);

        }

    else
    {
        throw new SkipException("");
    }

    }
    }


