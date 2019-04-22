
package parser_test2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class Parser_test2 {

    private static Document getPage() throws IOException {   // вывод кода всей страницы   
               
        String url;
        url="https://www.mvideo.ru/noutbuki-planshety-komputery/noutbuki-118/f/page=1";
        Document page=Jsoup.parse(new URL(url), 3000);

        return page;
        
    }
    
     private static String getTitle(String url) throws IOException {   // вывод заголовка страницы      
               

        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        return title;
        
    }   
     
     
     
     
          private static int getNumLastPage() throws IOException {   // Определение номера последней страницы  
               
        
        Document doc = Jsoup.connect("https://www.mvideo.ru/noutbuki-planshety-komputery/noutbuki-118/f/page=1").get();
        String Page = doc.select("a[class=c-pagination__num c-btn c-btn_white]").text();
        int NumLastPage=Integer.parseInt(Page);
        return NumLastPage;
        
    }   
     

            
 
         private static void getNameAndCost(String page) throws IOException {   // Вывод названия и стоимости товара
                    
                    Document Document;
                    Document = Jsoup.connect("https://www.mvideo.ru/noutbuki-planshety-komputery/noutbuki-118/f/"+page).get();
                    Elements ProdName = Document.select("a[class=sel-product-tile-title]");

                    Elements classCost = Document.select("div[class=c-pdp-price]");
                                     
                    for (Element PName : ProdName)
                    {
                       String Name = PName.select("a[title]").text();
                       System.out.println(Name);
                    }
                    
                                        
                    for (Element Cost : classCost)
                    {
                       String CostStr = Cost.select("div[class=c-pdp-price__current]").text();
                       System.out.println(CostStr);
                       
                       String costOnly= CostStr.replaceAll("[^0-9]", "");
                       int a;
                       if ("".equals(costOnly))                     //перевод пустых строк в формат Integer (если в дальнейшем в БД заливать, то может пригодиться)
                       {
                           a=0;
                       }
                       else
                       {
                           a = Integer.parseInt(costOnly);             
                       }
                       System.out.println(Integer.toString(a));
                    }

    }       
            
            
  
    
    public static void main(String[] args) throws IOException {
               
   /*     Document docTest;

        docTest = Jsoup.connect("https://www.mvideo.ru/").get();
        System.out.println(docTest.title());
        
        docTest = Jsoup.connect("https://www.eldorado.ru/").get();
        System.out.println(docTest.title());
        
        docTest = Jsoup.connect("https://www.dns-shop.ru/").get();
        System.out.println(docTest.title());
        */
  /*      System.out.println(getPage());
        Scanner scan = new Scanner(System.in);
       System.out.println("Введите url-страницу: ");
        
        String url = scan.nextLine();
        System.out.println(getTitle("https://www."+url));
    */    
/*        System.out.println(getCost());
        
        System.out.println(getProdName());*/
        
   //     System.out.println(getTest());
        
    /*    System.out.println(getСost2());
        System.out.println(getProdName2());*/
    String page = "page=";

    int LastPage = getNumLastPage();
    for (int i=0; i<LastPage; i++)
    {       
            System.out.println("Страница: "+Integer.toString(i+1));  
            getNameAndCost(page+Integer.toString(i+1));
                
    }
    
    }


    
    
    
}
