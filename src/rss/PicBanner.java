package rss;

import java.util.ArrayList;
import java.util.List;

import datamodel.NewsItemModel;

public class PicBanner extends NewsItemModel {
	
	private String title; 
	private String originalLink; 
	private List<String> picLinks; 
	private String description; 
	private long pubDate; 
	private String original; 
	private String timeStamp; 
	private String detailNo; 
	private int category;
	
	public String getPicLinkString() {
		StringBuffer sb = new StringBuffer();
		for(String str : picLinks) {
			sb.append(str).append("\n");
		}
		return sb.toString();
	}
	
	public void setPicLinkList(List<String> list) {
		this.picLinks = list;
	}
	
	public PicBanner() {
		this.picLinks = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return originalLink;
	}

	public List<String> getPicLinks() {
		return picLinks;
	}

	public long getPubDate() {
		return pubDate;
	}

	public String getOriginal() {
		return original;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public String getDetailNo() {
		return detailNo;
	}

	public int getCategory() {
		return category;
	}
	
	public String getDescription() {
		return description;
	}
	
	

	@Override
	public void setTitle(String title) {
		this.title = title;
		
	}

	@Override
	public void setLink(String link) {
		this.originalLink = link;
		
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPicLink(String picLink) {
		this.picLinks.add(picLink);
	}

	@Override
	public void setPubDate(long pubDate) {
		this.pubDate = pubDate;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public void setOriginal(String original) {
		this.original = original;
	}

	

	

	
	
	

}
