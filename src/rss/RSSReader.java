package rss;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import datamodel.NewsItem;
import rss.RssNormalHandler.ItemGetter;

public class RSSReader {
	
	public static List<NewsItem> getRSS(String string) {
		
		List<NewsItem> mRSSItems = new ArrayList<>();
		
		try {
			URL url = new URL(string);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			InputStream in = conn.getInputStream();
			SAXParserFactory factory = SAXParserFactory.newInstance();  
			SAXParser parser = factory.newSAXParser();  
			XMLReader xmlReader = parser.getXMLReader();
			RSSHandler mRSSHandler = new RSSHandler(mRSSItems);
			xmlReader.setContentHandler(mRSSHandler); 
			xmlReader.parse(new InputSource(in)); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mRSSItems;
		
	}
	
	public static List<PicBanner> getPicRss(String path) {
	
		List<PicBanner> picBanners = new ArrayList<>();
		File file = new File(path);
		try {
			InputStream in = new FileInputStream(file);
			SAXParserFactory factory = SAXParserFactory.newInstance();  
			SAXParser parser = factory.newSAXParser();  
			XMLReader xmlReader = parser.getXMLReader();
			
			RssNormalHandler<PicBanner> mRSSHandler = new RssNormalHandler<PicBanner>(picBanners, new ItemGetter<PicBanner>() {

				@Override
				public PicBanner getD() {
					
					return new PicBanner();
				}
				
			});
			
			xmlReader.setContentHandler(mRSSHandler); 
			xmlReader.parse(new InputSource(in)); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return picBanners;
	}
	
	
	
	public static void main(String[] args) {
//		List<PicBanner> picBanners = getPicRss("/Users/bym/Documents/NewFile.xml");
//		for(PicBanner banner : picBanners) {
//			System.out.println(banner.getPubDate());
//			System.out.println(banner.getDescription());
//			System.out.println(banner.getTitle());
//			System.out.println(banner.getLink());
//			System.out.println(banner.getPicLinks());
//		}
//		
	}
	

}












