package com.rabbitmq.rabbitmqdemo;

import java.io.Serializable;

public class SimpleMessage implements Serializable {
    private String name;
    private String simpleDescription;

    public SimpleMessage () {
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSimpleDescription () {
        return simpleDescription;
    }

    public void setSimpleDescription (String simpleDescription) {
        this.simpleDescription = simpleDescription;
    }

    @Override
    public String toString () {
        return "SimpleMessage{" +
                "name='" + name + '\'' +
                ", simpleDescription='" + simpleDescription + '\'' +
                '}';
    }
}
