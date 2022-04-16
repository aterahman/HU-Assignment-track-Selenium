import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class productlist extends weathershopper
{

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

        }



    }
