package rss;

import java.util.List;

import datamodel.NewsItemForClient;

public class NewsData {
	private String date;
    private String error;
    private String ErrorMessage;
    

    private List<NewsItemForClient> newsItems;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewsItemForClient> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItemForClient> newsItems) {
        this.newsItems = newsItems;
    }
	
}
