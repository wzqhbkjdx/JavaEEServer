package getdata;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

import database.MyDatabase;
import datamodel.NewsItemForClient;
import jsoupparse.JsoupParser;
import rss.NewsItem;
import rss.RSSReader;
import utils.DateGetter;
import utils.MyConstants;
import xmlparse.XmlParser;

public class DataResolver {
	
	/**
	 * 解析Rss源，并解析新闻的详细内容，将数据保存到newsItem的数据库表中，详细内容连接保存到newsDetail表中，详细内容保存到xml文件中，
	 * @param url rss源
	 * @param original 新闻来源
	 * @param xmlPath xml文件保存路径
	 * @param category 新闻类型
	 */
	public static synchronized void dataResolve(String url, String original,String xmlPath, int category) {
		
		//1.从Rss源获得一批新闻
		List<NewsItem> list = RSSReader.getRSS(url);
		List<NewsItemForClient> clientNewsList = new ArrayList<>();
		//2.对比数据库中的新闻列表的最新pubDate与获取到的rss新闻的pubdate 比该新闻新的进入数据库，比该新闻旧的抛弃
		//获取当前数据库中存在的pubDate的最大值
		long maxPubdate = MyDatabase.getMaxPubdate(category);
		for(NewsItem item : list) {
			
			long pubdate = DateGetter.parsePubdateToLong(DateGetter.parsePubdate(item.getPubDate()));
			//得到item的pubdate与maxpubdate进行比较
			if(pubdate > maxPubdate) {
				System.out.println(pubdate);
				NewsItemForClient newsItemForClient = new NewsItemForClient();
				newsItemForClient.setTitle(item.getTitle());
				newsItemForClient.setNewsDetailLink(item.getLink());
				newsItemForClient.setPubDate(DateGetter.parsePubdate(item.getPubDate()));
				newsItemForClient.setOriginal(original);
				newsItemForClient.setDetailNo(DateGetter.getIntegrateTime());
				newsItemForClient.setCategory(category);
				List<Element> elementList = JsoupParser.parseHtmlForXml(newsItemForClient.getNewsDetailLink());//用jsoup解析html文件的方式解析获取新闻详情并保存到List中
				newsItemForClient.setPicLink(getPicLink(elementList));//设置图片的链接
				XmlParser.createXmlFromList(newsItemForClient.getPubDate().toString(), elementList,xmlPath);//将新闻详情保存到xml文件，
				clientNewsList.add(newsItemForClient);
			}
			
		}

		MyDatabase.insertNewsItems(clientNewsList);
		
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
		long start = System.currentTimeMillis();
		dataResolve(MyConstants.RSSURLZGHKWLSJDHZ,"中国航空新闻网",MyConstants.XMLPATHOFLSJDHZ,MyConstants.CATEGORYZGHKWLSJDHZ);
		dataResolve(MyConstants.RSSURLZGHKWNEWSCENTER,"中国航空新闻网",MyConstants.XMLPATHOFNEWSCENTER,MyConstants.CATEGORYZGHKWNEWSCENTER);
		long end = System.currentTimeMillis();
		System.out.println("获取rss并保存数据共耗时：" + (end - start)/1000);
		
		
	}
}









