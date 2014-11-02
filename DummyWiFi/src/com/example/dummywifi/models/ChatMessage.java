package com.example.dummywifi.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by alex on 10/26/14.
 */
public class ChatMessage implements Serializable{
    private static final long serialVersionUID = -5967900946938355970L;
    public enum Types {MESSAGE,ACK,COMMAND};

    private final String text;
    private final Types type;
    private final UUID id;


    public ChatMessage(String text) {
        this.id = UUID.randomUUID();
        this.text = text;
        type = Types.MESSAGE;
    }
    
    public ChatMessage(String text, Types type)
    {
    	this.id = UUID.randomUUID();
        this.text = text;
    	this.type = type;
    }
    

    public String getText() {
        return text;
    }
    
    public Types getType()
    {
    	return type;
    }

    public UUID getId() {return id; }

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
