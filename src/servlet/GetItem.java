package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MyDatabase;
import datamodel.NewsItemForClient;
import fastjson.FastJsonTools;
import rss.NewsData;
import utils.DateGetter;
import utils.MyConstants;

@WebServlet("/GetItem")
public class GetItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetItem() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
		
		Map<String,String> map = getParas(request,response); //得到request中的参数
		if(map != null) {
			List<NewsItemForClient> list = getNewsItemsFromDB(map); //根据参数查询数据库后将得到的item封装为List
			
			//将获取的数据封装进NewsData数据模型中转换为Json格式返回给客户端
			NewsData data = new NewsData();
			if(list.size() > 0) {
				data.setDate(DateGetter.getDate());
				data.setError(MyConstants.NOERROR);
				data.setErrorMessage("");
				data.setNewsItems(list);
			} else {
				data.setDate(DateGetter.getDate());
				data.setError(MyConstants.ERROR);
				data.setErrorMessage("目前的数据是最新的");
			}
			
			sendDataToClient(data,request,response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	private void sendDataToClient(NewsData data,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
        PrintWriter writer = response.getWriter();
        
        String result = FastJsonTools.createJsonString(data);
        writer.print(result);
        System.out.print(result);
	}
	

	
	private Map<String,String> getParas(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException {
			Map<String,String> map = new HashMap<>();
	        String para = null;
	        Enumeration<String> e = request.getParameterNames();
	        while(e.hasMoreElements()) {
	            para = e.nextElement();
	            if(para != null) {
	                map.put(para, request.getParameter(para));
	            }
	        }
	        return map;
	 
	}
	
	private List<NewsItemForClient> getNewsItemsFromDB(Map<String,String> map) {
		List<NewsItemForClient> resultList = new ArrayList<>();
		List<NewsItemForClient> partList = new ArrayList<>();
		MyDatabase mdb = new MyDatabase();
		for(String str : map.keySet()) {
			System.out.println(str + "------>" + map.get(str));
			//将pubdate的值传递给数据库，查找到更新的新闻列表后返回
			if(str.equals(MyConstants.NEWSCENTER)) {
				partList = mdb.queryNewsItemsFromDatabase(map.get(str), MyConstants.CATEGORYZGHKWNEWSCENTER);
			} else if (str.equals(MyConstants.LSJDHZ)) {
				partList = mdb.queryNewsItemsFromDatabase(map.get(str), MyConstants.CATEGORYZGHKWLSJDHZ);
			}
			resultList.addAll(partList);
		}
		if(resultList.size() != 0) {
			System.out.println("resultList中有值：" + resultList.size());
		}
		return resultList;
	}
	


}
