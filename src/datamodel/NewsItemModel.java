package datamodel;

import java.util.List;

public abstract class NewsItemModel {
	
	public abstract void setTitle(String title);
	public abstract void setLink(String link);
	public abstract void setDescription(String description);
	public abstract void setPicLink(String picLink);
	public abstract void setPubDate(long pubDate);
	
	public abstract String getTitle();
	public abstract String getLink();
	public abstract String getDescription();
	public abstract List<String> getPicLinks();
	public abstract long getPubDate();
}
