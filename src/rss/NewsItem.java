package rss;

import java.util.List;

public class NewsItem {
//	private String title;
//	private String description;
//	private String link;
//	private String pubdate;
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getLink() {
//		return link;
//	}
//	public void setLink(String link) {
//		this.link = link;
//	}
//	public String getPubdate() {
//		return pubdate;
//	}
//	public void setPubdate(String pubdate) {
//		this.pubdate = pubdate;
//	}
//	
//	@Override
//	public String toString() {
//		return "RSSItem [title=" + title + ", link=" + link + ", pubdate=" + pubdate + 
//		", description" + description + "]";
//	}
	
	private String title;
    private String description;
    private String link;
    private String pubDate;
    private int type;
    private String original;
    private String picLink;
    
    private List<String> newsDetails;

    public List<String> getNewsDetails() {
		return newsDetails;
	}

	public void setNewsDetails(List<String> newsDetail) {
		this.newsDetails = newsDetail;
	}

	public NewsItem() {

    }

    public NewsItem(String pubDate,int type ) {
        this.pubDate = pubDate;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }
    
//    @Override
//    public String toString() {
//    	return "NewsItem [title=" + title + ", link=" + link + ", pubdate=" + pubDate + 
//    			", description" + description + "]";
//    }

}
