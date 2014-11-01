package com.example.dummywifi.models;

import java.io.Serializable;

/**
 * Created by alex on 10/26/14.
 */
public class ChatMessage implements Serializable{
    private static final long serialVersionUID = -5967900946938355970L;

    private final String text;
    private final int flag;

    public ChatMessage(String text) {
        this.text = text;
        flag = 0;
    }
    
    public ChatMessage(String text, int flag)
    {
    	this.text = text;
    	this.flag = flag;
    }
    

    public String getText() {
        return text;
    }
    
    public int getFlag()
    {
    	return flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatMessage)) return false;

        ChatMessage message = (ChatMessage) o;

        if (!text.equals(message.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
