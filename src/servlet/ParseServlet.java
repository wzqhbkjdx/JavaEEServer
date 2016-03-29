package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Element;

import jsoupparse.JsoupParser;
import utils.DateGetter;
import xmlparse.XmlParser;


@WebServlet("/ParseServlet")
public class ParseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String URL = "http://www.cannews.com.cn/2016/0328/150794.shtml";   
    
    public ParseServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsoupParser jp = new JsoupParser();
		List<Element> elementList = jp.parseHtmlForXml(URL);
		String nowDate = DateGetter.getDate();
		
		XmlParser.createXmlFromList(nowDate, elementList);
		XmlParser.parserXmltoMap(nowDate);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
