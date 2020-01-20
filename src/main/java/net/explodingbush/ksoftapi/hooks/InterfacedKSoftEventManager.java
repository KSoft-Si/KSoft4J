package net.explodingbush.ksoftapi.hooks;

import net.explodingbush.ksoftapi.entities.events.Event;
import net.explodingbush.ksoftapi.webhooks.WebhookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InterfacedKSoftEventManager implements IKSoftListenerManager {


    private static final ArrayList<KSoftListener> listeners = new ArrayList<>();

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException
     *         If the provided listener does not implement {@link KSoftListener KSoftListener}
     */
    @Override
    public void register(Object listener)
    {
        if (!(listener instanceof KSoftListener)) {
            throw new IllegalArgumentException("Listener must implement KSoftListener");
        }
        listeners.add((KSoftListener) listener);
    }

    @Override
    public void unregister(Object listener) {
        listeners.remove(listener);
    }


    @Override
    public List<Object> getRegisteredListeners() {
        return Collections.unmodifiableList(new LinkedList<>(listeners));
    }

    @Override
    public void handle(Event event) {
        for (KSoftListener listener : listeners) {
            try {
                listener.onEvent(event);
            } catch (Throwable throwable) {
                new WebhookService(null).getLogger().error("One of the KSoftListeners had an uncaught exception", throwable);
            }
        }
    }

}
