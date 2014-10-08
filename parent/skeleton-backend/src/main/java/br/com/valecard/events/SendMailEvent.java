/*
 * The License
 *
 * Copyright 2014 Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 *
 * This file is part of schedule-app and can not be copied and/or 
 * distributed without the express permission of Marcos O. Junqueira <marcos.junqueira at gmail.com>.
 */
package br.com.valecard.events;

import org.springframework.context.ApplicationEvent;

/**
 *
 * @author Marcos O. Junqueira <marcos.junqueira at gmail.com>
 */
public class SendMailEvent extends ApplicationEvent {

    private String property;

    public SendMailEvent(String property, Object source) {
        super(source);
        this.property = property;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

}
