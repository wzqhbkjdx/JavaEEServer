package getdata;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import datamodel.NewsItemForClient;
import jsoupparse.JsoupParser;
import rss.NewsItem;
import rss.RSSReader;
import utils.DateGetter;
import xmlparse.XmlParser;

public class DataResolver {
	
	public static synchronized void dataResolve(String url, String original) {
		
		//解析RSS
		List<NewsItem> list = RSSReader.getRSS(url);
		List<NewsItemForClient> clientNewsList = new ArrayList<>();
		
		//将解析好的RSSitem转换为发送给客户端的数据Model
		for(NewsItem item : list) {
			
			NewsItemForClient newsItemForClient = new NewsItemForClient();
			
			newsItemForClient.setTitle(item.getTitle());
			newsItemForClient.setNewsDetailLink(item.getLink());
			newsItemForClient.setPubDate(item.getPubDate());
			newsItemForClient.setOriginal(original);
			newsItemForClient.setTimeStamp(DateGetter.getIntegrateTime());
			
			//NewsItemForClient没有直接获取到的detailNo以及picLink需要进一步获取
			//且同时生成detailNo对应的xml文件 并将detailNo以及xml文件的路径存入数据库
			
			//用jsoup解析html文件的方式解析获取新闻详情
			List<Element> elementList = JsoupParser.parseHtmlForXml(newsItemForClient.getNewsDetailLink());
			//设置图片的链接
			newsItemForClient.setPicLink(getPicLink(elementList));
			//将新闻详情保存到xml文件中，并将相关路径信息存入数据库
			XmlParser.createXmlFromList(newsItemForClient.getTimeStamp(), elementList);
			
			clientNewsList.add(newsItemForClient);
			
			
			/**
			 * 思路：
			 * 0.修改NewsItemForClient数据格式pubDate为long型
			 * 1.先判断新闻是否值得存 通过pubdate比较
			 * 2.然后保存该新闻，保存该新闻后再保存新闻详情到xml文件，同时存储索引数据库
			 */
			
		}
		
		
		
		//NewsItemForClient获取完整后也存入数据库
		
		
		
	}
	
	private static String getPicLink(List<Element> list) {
		String picLink = "";
		for(Element e : list) {
			if(e.nodeName().equals("img")) {
				picLink = e.attr("src");
				break;
			}
		}
		return picLink;
	}
	
	public static void main(String[] args) throws ParseException {
//		long start = System.currentTimeMillis();
//		dataResolve(MyConstants.RSSURLZGHKW,"中国航空新闻网");
//		long end = System.currentTimeMillis();
//		System.out.println("获取rss并保存数据共耗时：" + (end - start)/1000);
		
		
	}
}









