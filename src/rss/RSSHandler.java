package rss;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import datamodel.NewsItem;
import utils.DateGetter;

public class RSSHandler extends DefaultHandler {
	
	private NewsItem mRSSItem;  
    private List<NewsItem> mRSSItems;
      
    private final int TITLE_STATE = 1;  
    private final int DESCRIPTION_STATE = 2;  
    private final int LINK_STATE = 3;  
    private final int PUBDATE_STATE = 4;  
      
    private int currentState; 
      
    public RSSHandler(List<NewsItem> mRSSItems) {  
    	this.mRSSItems=mRSSItems;
        currentState = 0;   
    }      
    public void startDocument () {  
    	
        mRSSItem = new NewsItem();
    }  
    public void endDocument () {  
          
    }  
   
    public void startElement (String uri, String localName, String qName, Attributes attributes) {  
        if (qName.equals("channel")) {  
            return ;  
        }     
        if (qName.equals("item")) {  
            mRSSItem = new NewsItem();  
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
    }  
      
    public void endElement (String uri, String localName, String qName) {  
    	if(qName.equals("item"))
    		mRSSItems.add(mRSSItem);
    }   
    public void characters (char[] ch, int start, int length) {  
        String str = new String(ch, start, length);  
        switch(currentState){  
        case TITLE_STATE:  
            mRSSItem.setTitle(str);  
            currentState = 0;  
            break;                
        case LINK_STATE:  
            mRSSItem.setNewsDetailLink(str);
            currentState = 0;  
            break;      
        case DESCRIPTION_STATE:  
//            mRSSItem.setDescription(str);  
            currentState = 0;  
            break;      
        case PUBDATE_STATE:  
            mRSSItem.setPubDate(DateGetter.parsePubdateToLong(str));  
            currentState = 0;  
            break;  
        }  
    }

}
