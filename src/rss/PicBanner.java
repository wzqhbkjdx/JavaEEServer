package rss;

import org.jsoup.Jsoup;

public class PicBanner {
	
	private String title;
    private String link;
    private String pubDate;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
        
    }


    public String getLink() {
        return link;
    }


    public String getTitle() {
        return title;
    }


    public String getPubDate() {
        return pubDate;
    }


    public String getDescription() {
        return description;
    }
    
//    @Override
//    public String toString() {
//    	return "PicBanner [title=" + title + ", link=" + link + ", pubdate=" + pubDate + 
//    			", description" + description + "]";
//    }

}
