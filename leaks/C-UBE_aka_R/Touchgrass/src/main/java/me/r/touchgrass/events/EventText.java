package me.r.touchgrass.events;

import com.darkmagician6.eventapi.events.Event;

/**
 * Created by r on 24/12/2021
 */
public class EventText implements Event {

    private String text;

    public EventText(final String text) {
        this.text = text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
