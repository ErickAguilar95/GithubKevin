package webscraping2;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class WebScraping2 {
    
    public static void main(String[] args) throws IOException, SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Ingrese que quiere buscar en Amazon: ");
        String search = in.nextLine();
        final String url = "https://www.amazon.com/";
        try(WebClient webClient = new WebClient(BrowserVersion.CHROME)){
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            HtmlPage page1 = webClient.getPage(url);
            HtmlForm form = page1.getFormByName("site-search");
            HtmlSubmitInput button = form.getInputByValue("Go");
            HtmlTextInput textField = form.getInputByName("field-keywords");
            textField.type(search);
            HtmlPage page2 = button.click();
            List<HtmlDivision> div = page2.getByXPath("//*[@id=\"search\"]/div[1]/div[1]/div/span[4]/div[1]/div");
            for(HtmlDivision temp : div){
                List<HtmlDivision> div2 = temp.getByXPath("//div/span/div/div/div[2]/div[2]/div");
                for(HtmlDivision temp2 : div2){
                    List<HtmlSpan> spanDesc = temp2.getByXPath("//div/div/div[1]/h2/a/span");
                    for(HtmlSpan temp3 : spanDesc){
                        String textDesc = temp3.getTextContent();
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebScraping", "root", "");
                        String sQry = "insert into Amazon value('"+ search + "', '" + textDesc +"');";
                        PreparedStatement post = conn.prepareStatement(sQry);
                        post.execute();
                    }
                    break;
                }
                break;
            }
        }
    }
}
