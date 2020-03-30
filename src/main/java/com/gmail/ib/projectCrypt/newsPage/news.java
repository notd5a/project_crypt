package com.gmail.ib.projectCrypt.newsPage;

public class news {

    private String title;
    private String body;
    private String source;

    public news(String title, String body, String source) {
        this.title = title;
        this.body = body;
        this.source = source;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
