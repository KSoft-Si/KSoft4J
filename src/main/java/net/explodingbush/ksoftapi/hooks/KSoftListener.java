package net.explodingbush.ksoftapi.hooks;

import net.explodingbush.ksoftapi.entities.events.Event;

@FunctionalInterface
public interface KSoftListener {

    void onEvent(Event event);

}
