package com.dev.app.chat.model;

import java.util.Date;


public class Message {

    private String id;
    private String name;
    private Date date;
    private String message;
    private boolean isMe;

    public Message() {
    }

    public Message(String id, String name, Date date, String message, boolean isMe) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.message = message;
        this.isMe = isMe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
