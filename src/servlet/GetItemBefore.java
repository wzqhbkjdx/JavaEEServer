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
import datamodel.NewsItem;
import datamodel.PicBannerForClient;
import fastjson.FastJsonTools;
import rss.NewsData;
import utils.DateGetter;
import utils.MyConstants;


@WebServlet("/GetItemBefore")
public class GetItemBefore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetItemBefore() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
        Map<String,String> map = getParas(request,response); //得到request中的参数
        
        if(map != null) {
			NewsData data = getNewsItemsFromDB(map); //根据参数查询数据库后将得到的item封装为List
			
			//将获取的数据封装进NewsData数据模型中转换为Json格式返回给客户端
			sendDataToClient(data,request,response);
		}
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 向用户发送数据 格式为Json
	 * @param data
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void sendDataToClient(NewsData data,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        PrintWriter writer = response.getWriter();
        
        String result = FastJsonTools.createJsonString(data);
        writer.print(result);
        System.out.print(result);
	}
	
	/**
	 * 从request中获取请求参数 键值对
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
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
	
	/**
	 * 从数据库中获取新闻列表
	 * @param map
	 * @return
	 */
	private NewsData getNewsItemsFromDB(Map<String,String> map) {
		NewsData data = new NewsData();
		List<NewsItem> resultList = new ArrayList<>();
		List<NewsItem> partList = new ArrayList<>();
		List<PicBannerForClient> picBanners = new ArrayList<>();
		MyDatabase mdb = new MyDatabase();
		for(String str : map.keySet()) {
			System.out.println(str + "------>" + map.get(str));
			//将pubdate的值传递给数据库，查找到更新的新闻列表后返回
			if(str.equals(MyConstants.NEWSCENTER)) {
				partList = mdb.queryHisNewsItemsFromDatabase(map.get(str), MyConstants.CATEGORYZGHKWNEWSCENTER);
			} else if (str.equals(MyConstants.LSJDHZ)) {
				partList = mdb.queryHisNewsItemsFromDatabase(map.get(str), MyConstants.CATEGORYZGHKWLSJDHZ);
			} 
			resultList.addAll(partList);
		}
		
		if(resultList.size() > 0 || picBanners.size() > 0) {
			data.setDate(Long.valueOf(DateGetter.getDate()));
			data.setErrorType(MyConstants.NOERROR);
			data.setNewsItems(resultList);
			data.setPicBanners(picBanners);
		} else {
			data.setDate(Long.valueOf(DateGetter.getDate()));
			data.setErrorType(MyConstants.ERRORNOMOREHISDATE);
		}
		
//		if(resultList.size() != 0) {
//			System.out.println("resultList中有值：" + resultList.size());
//		}
		return data;
	}

}
