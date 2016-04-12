package rss;

import java.util.ArrayList;
import java.util.List;

import datamodel.NewsItem;
import datamodel.PicBannerForClient;

public class NewsData {
	private long date;
    private int errorType;
    
    private List<NewsItem> newsItems;
    private List<PicBannerForClient> picBanners;
    
    public NewsData() {
    	this.newsItems = new ArrayList<NewsItem>();
    	this.picBanners = new ArrayList<PicBannerForClient>();
    	
    }

    public List<PicBannerForClient> getPicBanners() {
		return picBanners;
	}

	public void setPicBanners(List<PicBannerForClient> picBanners) {
		this.picBanners = picBanners;
	}

	

    public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
	
}
