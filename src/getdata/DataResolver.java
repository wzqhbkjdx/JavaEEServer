package getdata;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import database.MyDatabase;
import datamodel.NewsItem;
import jsoupparse.JsoupParser;
import rss.PicBanner;
import rss.RSSReader;
import utils.DateGetter;
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
		List<NewsItem> clientNewsList = new ArrayList<>();
		//2.对比数据库中的新闻列表的最新pubDate与获取到的rss新闻的pubdate 比该新闻新的进入数据库，比该新闻旧的抛弃
		//获取当前数据库中存在的pubDate的最大值
		long maxPubdate = MyDatabase.getMaxPubdateFromNewsItem(category);
		for(NewsItem item : list) {
			
			long pubdate = item.getPubDate();
			//得到item的pubdate与maxpubdate进行比较
			if(pubdate > maxPubdate) {
//				System.out.println(pubdate);
				NewsItem newsItemForClient = new NewsItem();
				newsItemForClient.setTitle(item.getTitle());
				newsItemForClient.setNewsDetailLink(item.getNewsDetailLink());
				newsItemForClient.setPubDate(item.getPubDate());
				newsItemForClient.setOriginal(original);
				newsItemForClient.setDetailNo(DateGetter.getIntegrateTime());
				newsItemForClient.setCategory(category);
				List<Element> elementList = JsoupParser.parseHtmlForXml(newsItemForClient.getNewsDetailLink());//用jsoup解析html文件的方式解析获取新闻详情并保存到List中
				newsItemForClient.setPicLink(getPicLink(elementList));//设置图片的链接
				XmlParser.createXmlFromList(String.valueOf(newsItemForClient.getPubDate()), elementList,xmlPath);//将新闻详情保存到xml文件，
				clientNewsList.add(newsItemForClient);
			}
			
		}

		MyDatabase.insertNewsItems(clientNewsList);
		
	}
	
	public static synchronized void picDataResolve(String picXmlPath, String original, int category) {
		//1.从xml文件中获取picBanner的信息
		List<PicBanner> picList = RSSReader.getPicRss(picXmlPath);
		List<PicBanner> bannerList = new ArrayList();
		//2.对比数据库中的新闻列表的最新pubDate与获取到的图片新闻的pubdate比该新闻新的进入数据库，比该新闻旧的抛弃
		long maxPubdate = MyDatabase.getMaxPubdateFromPicItem(category);
		//3.逐个对比，存入数据库
		for(PicBanner banner : picList) {
			long pubdate = banner.getPubDate();
			if(pubdate > maxPubdate) {
				PicBanner picBanner = new PicBanner();
				picBanner.setTitle(banner.getTitle());
				picBanner.setLink(banner.getLink());
				picBanner.setPicLinkList(banner.getPicLinks());
				picBanner.setPubDate(banner.getPubDate());
				picBanner.setCategory(category);
				picBanner.setOriginal(original);
				picBanner.setDetailNo(DateGetter.getIntegrateTime());
				picBanner.setDescription(banner.getDescription());
				bannerList.add(picBanner);
			}
		}
		MyDatabase.insertPicItems(bannerList);
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
		picDataResolve("/Users/bym/Documents/NewFile.xml", "中国航空新闻网", 1);
		
		
	}
}









