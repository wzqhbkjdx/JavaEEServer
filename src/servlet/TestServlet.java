package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fastjson.FastJsonTools;
import jsoupparse.JsoupParser;
import rss.NewsData;
import rss.NewsItem;
import rss.RSSReader;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String RSS = "http://blog.sina.com.cn/rss/1267454277.xml";//新浪
	private static final String RSS2 = "http://www.prnasia.com/story/industry/AIR-1.atom";//霍尼韦尔
	private static final String RSS3 = "http://sse.tongji.edu.cn/SSEMainRSS.aspx";//同济大学
	private static final String RSS4 = "http://app.cannews.com.cn/?app=rss&controller=index&action=feed&catid=27";//中国航空新闻网
	
	
	
    public TestServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long start = System.currentTimeMillis();
		
		response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
        PrintWriter writer = response.getWriter();
        
        //读取Rss源并解析
		List<NewsItem> list = RSSReader.getRSS(RSS4);
		//获取Rss源中的html文件
//		JsoupParser.setNewsDetail(list);
		
		NewsData newsData = new NewsData();
		String date = String.valueOf(System.currentTimeMillis());
		newsData.setDate(date);
		newsData.setNewsItems(list);
		
		String result = FastJsonTools.createJsonString(newsData);
		writer.println(result);
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		long timeSpend = (end - start) / 1000;
		System.out.println("timeSpend:" + timeSpend + "s");
		
		writer.flush();
		writer.close();
		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
