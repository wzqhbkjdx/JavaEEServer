package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fastjson.FastJsonTools;
import xmlparse.XmlParser;

/**
 * Servlet implementation class GetNewsDetail
 */
@WebServlet("/GetNewsDetail")
public class GetNewsDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNewsDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");  
		request.setCharacterEncoding("utf-8");  
		response.setCharacterEncoding("utf-8");  
		PrintWriter writer = response.getWriter();
		
		Map<String,String> map = getParas(request,response); //得到request中的参数
		if(map != null) {
			Map<String,String> newsDetailMap = getNewsDetailFromXml(map);
			String result = FastJsonTools.createJsonString(newsDetailMap);
			writer.write(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	
	private Map<String,String> getNewsDetailFromXml(Map<String,String> map) {
		Map<String,String> newsDetailMap = new LinkedHashMap<>();
		for(String str : map.keySet()) {
			if(str.equals("detailNo")) {
				String detailNo = map.get(str);
			newsDetailMap = XmlParser.parserXmltoMap(detailNo);
			}
		}
		return newsDetailMap;
	}

}
