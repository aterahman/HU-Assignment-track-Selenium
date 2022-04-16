import org.openqa.selenium.WebElement;
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


    ArrayList<String> productnames = new ArrayList<>();
    ArrayList<String> productprices = new ArrayList<>();
    ArrayList<WebElement> buttons = new ArrayList<>();
    ArrayList<String> aloe = new ArrayList<>();
    ArrayList<String> almond = new ArrayList<>();
    ArrayList<String> spf30 = new ArrayList<>();
    ArrayList<String> spf50 = new ArrayList<>();
    ArrayList<String> aloeprice = new ArrayList<>();
    ArrayList<String> almondprice = new ArrayList<>();
    ArrayList<String> spf30price = new ArrayList<>();
    ArrayList<String> spf50price = new ArrayList<>();
    ArrayList<WebElement> aloebuttons = new ArrayList<>();
    ArrayList<WebElement> almondbuttons = new ArrayList<>();
    ArrayList<WebElement> spf30buttons = new ArrayList<>();
    ArrayList<WebElement> spf50buttons = new ArrayList<>();



    public void storeproducts() {

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

    public void sortproducts() {
        for (int i = 0; i < productnames.size(); i++) {
            String product = productnames.get(i);
            String price = productprices.get(i);
            if (product.contains("Aloe")) {
                aloe.add(product);
                aloeprice.add(product);
            }
            else if (product.contains("Almond")) {
                almond.add(product);
                almondprice.add(product);
            }
            else if (product.contains("SPF-30")) {
                spf30.add(product);
                spf30price.add(product);
            }
           else if (product.contains("SPF-50")) {
                spf50.add(product);
                spf50price.add(product);
            }

        }

    }

    public void comparemoisturizers()
    {
        int leastaloeprice = 0;
        for(int i =0;i<aloeprice.size();i++)
        {
            int price = Integer.parseInt(aloeprice.get(i));

            for(int j=i+1;j<aloeprice.size();j++)
            {
                int price1 = Integer.parseInt(aloeprice.get(j));
                if(price>price1)
                {
                    int temp = price1;
                    price1= leastaloeprice;
                    leastaloeprice=price1;
                }
            }
        }
        int leastalmondprice = 0;
        for(int i =0;i<almondprice.size();i++)
        {
            int price = Integer.parseInt(aloeprice.get(i));

            for(int j=i+1;j<aloeprice.size();j++)
            {
                int price1 = Integer.parseInt(aloeprice.get(j));
                if(price>price1)
                {
                    leastalmondprice=j;
                }
            }
        }
    }
}

