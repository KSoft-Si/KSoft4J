package net.explodingbush.ksoftapi.hooks;

import net.explodingbush.ksoftapi.entities.events.BanEvent;
import net.explodingbush.ksoftapi.entities.events.Event;
import net.explodingbush.ksoftapi.entities.events.UnbanEvent;
import net.explodingbush.ksoftapi.entities.events.VoteEvent;

public abstract class KSoftListenerAdapter implements KSoftListener {

    public void onBan(BanEvent event) {
    }

    public void onUnban(UnbanEvent event) {
    }

    public void onVote(VoteEvent event) {
    }

    public void onGenericEvent(Event event) {
    }

    @Override
    public final void onEvent(Event event) {
        onGenericEvent(event);
        if (event instanceof BanEvent) {
            onBan((BanEvent) event);
        } else if (event instanceof UnbanEvent) {
            onUnban((UnbanEvent) event);
        } else if (event instanceof VoteEvent) {
            onVote((VoteEvent) event);
        }
    }
}
