package rss;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import datamodel.NewsItem;
import datamodel.NewsItemModel;
import utils.DateGetter;

public class RssNormalHandler<T extends NewsItemModel> extends DefaultHandler {
	
	private T newsItem;
    private List<T> newsItems;
	
    private final int TITLE_STATE = 1;  
    private final int DESCRIPTION_STATE = 2;  
    private final int LINK_STATE = 3;  
    private final int PUBDATE_STATE = 4;
    private final int PICLINK_STATE = 5;
    private ItemGetter itemGetter;
    private int currentState;
    
    
    public RssNormalHandler(List<T> newsItems,ItemGetter itemGetter) {
    	this.newsItems = newsItems;
    	this.itemGetter = itemGetter;
    	currentState = 0;
    }
    
    private T getT() {
    	return (T) itemGetter.getD(); //开放接口给调用者，便于以后的复用
    }
    
    public interface ItemGetter<D extends NewsItemModel> { //开放接口给调用者，便于以后的复用
    	public D getD();
    }
    
    @Override
    public void startDocument() throws SAXException {
    	newsItem = getT();
    }
    
	@Override
	public void endDocument() throws SAXException {
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equals("channel")) {  
            return ;  
        }     
        if (qName.equals("item")) {  
        	newsItem = getT();  
            return;  
        }  
        if (qName.equals("title")) {  
            currentState = TITLE_STATE;  
            return ;  
        }            
        if (qName.equals("description")) {  
            currentState = DESCRIPTION_STATE;  
            return ;  
        }  
        if (qName.equals("link")) {  
            currentState = LINK_STATE;  
            return ;  
        }           
        if (qName.equals("pubDate")) {  
            currentState = PUBDATE_STATE;  
            return ;  
        }
        if (qName.equals("picLink")) {
        	currentState = PICLINK_STATE;
        	return ;
        }
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("item"))
    		newsItems.add(newsItem);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String str = new String(ch, start, length);  
        switch(currentState){  
        case TITLE_STATE:  
        	newsItem.setTitle(str);  
            currentState = 0;  
            break;                
        case LINK_STATE:  
        	newsItem.setLink(str);
            currentState = 0;  
            break;      
        case DESCRIPTION_STATE:  
        	newsItem.setDescription(str);  
            currentState = 0;  
            break;      
        case PUBDATE_STATE:  
        	newsItem.setPubDate(DateGetter.parsePubdateToLong(str));  
            currentState = 0;  
            break;
        case PICLINK_STATE:
        	newsItem.setPicLink(str);
        	currentState = 0; 
        	break;
        }  
		
	}
}











