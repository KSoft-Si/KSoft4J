package net.explodingbush.ksoftapi.entities.events;

import net.explodingbush.ksoftapi.KSoftAPI;

public abstract class Event {

    private final KSoftAPI api;

    public Event(KSoftAPI api) {
        this.api = api;
    }
    public KSoftAPI getKSoftAPI() {
        return api;
    }
}
