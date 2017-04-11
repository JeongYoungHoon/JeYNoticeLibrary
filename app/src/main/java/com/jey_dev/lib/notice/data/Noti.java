/*
 * Copyright (c) 2015. WeNoun™. TANSAN, Since 2014.
 * Code By Jey.
 */

package com.jey_dev.lib.notice.data;

import java.io.Serializable;

/**
 * Created by SnakeJey on 2015-01-27.
 */
public class Noti extends Object implements Serializable{
    private int no=-1;
    private String title="";
    private String contentUrl="";
    private String date="198901020000"; // date형식 : yyyyMMddhhmm
    public Noti(int no, String title, String contentUrl, String date){
        this.no=no;
        this.title=title;
        this.contentUrl=contentUrl;
        this.date=date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getDate() {
        String month=date.substring(4,6);
        String day=date.substring(6,8);
        return month+"/"+day;
    }
    public String getDateFull() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
