package com.myself.latte.delegates.web.event;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.HashMap;

/**
 * Created by Kamh on 2018/3/26.
 */

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NotNull String name, Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NotNull String action) {
        Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
