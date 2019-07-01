
package parser_test2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;
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
     

            
 
         private static void getNameAndCost(String page) throws IOException, SQLException {   // Вывод названия и стоимости товара
            //////////////////       
     Date date = new Date();  
     System.out.println(date.toString()); 
     
      
     
     String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
     String USER = "postgres";
     String PASS = "postgres"; 
     System.out.println("Testing connection to PostgreSQL JDBC");
 
	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
		e.printStackTrace();
		return;
	}
 
	System.out.println("PostgreSQL JDBC Driver successfully connected");
	Connection connection = null;
 
	try {
		connection = DriverManager
		.getConnection(DB_URL, USER, PASS);
 
	} catch (SQLException e) {
		System.out.println("Connection Failed");
		e.printStackTrace();
		return;
	}
 
	if (connection != null) {
		System.out.println("You successfully connected to database now");
	} else {
		System.out.println("Failed to make connection to database");
	} 
        Statement stmt = connection.createStatement();
        
        
             /////////////////
                    Document Document;
                    Document = Jsoup.connect("https://www.mvideo.ru/noutbuki-planshety-komputery/noutbuki-118/f/"+page).get();
                    Elements ProdName = Document.select("a[class=sel-product-tile-title]");

                    Elements classCost = Document.select("div[class=c-pdp-price]");
                    
                    TreeMap <String, Integer> map;
                    map = new TreeMap<String, Integer>();
                                        
                    
                    for (int i=0; i<ProdName.size(); i++)
                    {       
                       String Name = ProdName.get(i).select("a[title]").text();
                       System.out.println(Name);
                       
                       
                       String CostStr = classCost.get(i).select("div[class=c-pdp-price__current]").text();
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
                       map.put(Name, a);
                       
                      stmt.executeUpdate("INSERT INTO DATE (DATE_TIME, Name, PRICE) VALUES ('"+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDay()+"','" + Name + "'," +a+  ");");
                       
                       
              //         System.out.println(map.toString());       
                    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
 /*                           
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
                    }*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    } 
         
            private static void getDataBaseInfo() throws IOException, SQLException {   // Определение номера последней страницы  
               
        
           Date date = new Date();  
     System.out.println(date.toString());   
        
     String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
     String USER = "postgres";
     String PASS = "postgres"; 
     System.out.println("Testing connection to PostgreSQL JDBC");
 
	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
		e.printStackTrace();
		return;
	}
 
	System.out.println("PostgreSQL JDBC Driver successfully connected");
	Connection connection = null;
 
	try {
		connection = DriverManager
		.getConnection(DB_URL, USER, PASS);
 
	} catch (SQLException e) {
		System.out.println("Connection Failed");
		e.printStackTrace();
		return;
	}
 
	if (connection != null) {
		System.out.println("You successfully connected to database now");
	} else {
		System.out.println("Failed to make connection to database");
	}  
        
              Statement stmt = connection.createStatement();
     //   stmt.executeUpdate("INSERT INTO Products (Name) VALUES ('azaza');");
        ResultSet rs = stmt.executeQuery("SELECT * FROM DATE");

            while (rs.next()){
            System.out.println(rs.getString("date_time") + " "+rs.getString("name")+" "+rs.getString("price"));
            };
        
    }             
            
  
    
    public static void main(String[] args) throws IOException, SQLException {
 
 
 
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
    
getDataBaseInfo();
    
}
}