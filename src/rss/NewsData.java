package rss;

import java.util.List;

public class NewsData {
	private String date;
    private String error;
    private String ErrorType;
    private String ErrorMessage;

    private List<NewsItem> newsItems;
    private List<PicBanner> banners;

    public List<PicBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<PicBanner> banners) {
        this.banners = banners;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorType() {
        return ErrorType;
    }

    public void setErrorType(String errorType) {
        ErrorType = errorType;
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

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
	
}
