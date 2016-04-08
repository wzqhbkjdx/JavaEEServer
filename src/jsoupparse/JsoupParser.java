package jsoupparse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupParser {
	
	
	private List<String> parseHtml(String url) {
		 List<Element> elementList = new ArrayList<>();
		 List<String> newsDetail = new ArrayList<>();
		try 
		{
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.getAllElements();
					
			for(Element e : elements) {
				String nodeName = e.nodeName();
	            if(nodeName.equals("p")) {
	            	elementList.add(e);
	                Elements imgElements = e.select("img");
	                for(Element ele : imgElements) {
	                	elementList.add(ele);
	                	
	                }
	                    	
	            }
	                    
			}
			for(Element e : elementList) {
				if(e.nodeName().equals("p")) {
					newsDetail.add(e.text());
				}
				if(e.nodeName().equals("img")) {
					newsDetail.add(e.attr("src"));
				}
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsDetail;

	}
	
	public static List<Element> parseHtmlForXml(String url) {
		List<Element> elementList = new ArrayList<>();
		try 
		{
			if(url != null) {
				Document doc = Jsoup.connect(url).timeout(10000).get();
				Elements elements = doc.getAllElements();
						
				for(Element e : elements) {
					String nodeName = e.nodeName();
		            if(nodeName.equals("p")) {
		            	elementList.add(e);
		                Elements imgElements = e.select("img");
		                for(Element ele : imgElements) {
		                	elementList.add(ele);
		                }
		            }
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			//如果url为空，则无法解析，所以这里用try catch 捕捉所有的异常
		}
		
//		for(Element e : elementList) {
//			System.out.println(e.toString());
//		}
		
		return elementList;

	}
	
//	public void setNewsDetail(List<NewsItem> newsItems) {
//		
//		for(NewsItem item : newsItems) {
//			item.setNewsDetails(parseHtml(item.getNewsDetailLink()));
//			
//			//test
////			List<String> list = item.getNewsDetails();
////			Iterator<String> iterator = list.iterator();
////			while(iterator.hasNext()){
////				System.out.println(iterator.next());
////			}
//		}
//	}
	

}











