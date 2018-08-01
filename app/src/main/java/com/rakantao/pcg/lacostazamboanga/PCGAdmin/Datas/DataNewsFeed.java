package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas;

public class DataNewsFeed {

    private String article_title;
    private String article_content;
    private String article_userID;
    private String date_posted;
    private String counter;
    private String audience;
    private String pushKey;

    public DataNewsFeed(String article_title, String article_content, String article_userID, String date_posted, String counter, String audience, String pushKey) {
        this.article_title = article_title;
        this.article_content = article_content;
        this.article_userID = article_userID;
        this.date_posted = date_posted;
        this.counter = counter;
        this.audience = audience;
        this.pushKey = pushKey;
    }

    public DataNewsFeed() {
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_userID() {
        return article_userID;
    }

    public void setArticle_userID(String article_userID) {
        this.article_userID = article_userID;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(String date_posted) {
        this.date_posted = date_posted;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }
}
