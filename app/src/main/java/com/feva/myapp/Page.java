package com.feva.myapp;

public class Page {
    public int pageId;
    public String pageTitle;
    public String pageContent;
    public String pageDate;
    public String pageThumbnailUrl;

    public Page(int pageId,
                String pageTitle,
                String pageContent,
                String pageDate,
                String pageThumbnailUrl) {
        this.pageId = pageId;
        this.pageTitle = pageTitle;
        this.pageDate = pageDate;
        this.pageContent = pageContent;
        this.pageThumbnailUrl = pageThumbnailUrl;
    }

    public int getId(){
        return this.pageId;
    }

    public String getTitle(){
        return this.pageTitle;
    }

    public String getDate(){
        return this.pageDate;
    }

    public String getContent(){
        return this.pageContent;
    }

    public String getThumbnail(){
        return this.pageThumbnailUrl;
    }
}
