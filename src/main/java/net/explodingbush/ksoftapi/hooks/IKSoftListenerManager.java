package net.explodingbush.ksoftapi.hooks;

import net.explodingbush.ksoftapi.entities.events.Event;

import java.util.List;

public interface IKSoftListenerManager {

    /**
     * Registers the specified listener
     * <br>Accepted types may be specified by implementations
     *
     * @param listener
     *        A listener object
     */
    void register(Object listener);

    /**
     * Removes the specified listener
     *
     * @param listener
     *        The listener object to remove
     */
    void unregister(Object listener);

    /**
     * Handles the provided {@link Event Event}.
     * How this is handled is specified by the implementation.
     *
     * @param event
     *        The event to handle
     */
    void handle(Event event);

    /**
     * The currently registered listeners
     *
     * @return An immutable list of listeners
     *         that have already been registered
     */
    List<Object> getRegisteredListeners();

}
