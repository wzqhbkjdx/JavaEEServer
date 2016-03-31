package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Element;

import database.MyDatabase;
import fastjson.FastJsonTools;
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
//		JsoupParser jp = new JsoupParser();
		//用jsoup解析html文件的方式解析获取新闻详情
//		List<Element> elementList = JsoupParser.parseHtmlForXml(URL);
//		String nowDate = DateGetter.getDate();
//		
//		response.setContentType("text/html;charset=utf-8");  
//        request.setCharacterEncoding("utf-8");  
//        response.setCharacterEncoding("utf-8");  
//        PrintWriter writer = response.getWriter();
		
		//将新闻详情保存到xml文件中，并将相关路径信息存入数据库
//		XmlParser.createXmlFromList(nowDate.trim(), elementList);
		//通过数据库获取本地的xml数据path
//		String path = MyDatabase.queryFromDatabase(nowDate);
//		Map<String, String> map = XmlParser.parserXmltoMap(nowDate);
//		String result = FastJsonTools.createJsonString(map);
//		
//		writer.write(result);
//		
//		System.out.println(result);
		
		//读取本地xml文件发送给客户端
//		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
//		response.setCharacterEncoding("utf-8");
//        response.setContentType("text/xml;charset=utf-8");
//		OutputStream out = response.getOutputStream();
//		byte[] buffer = new byte[1024];
//		int len = 0;
//		while((len = bis.read(buffer)) > 0) {
//			out.write(buffer,0,len);
//			out.flush();
//		}
//		bis.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
