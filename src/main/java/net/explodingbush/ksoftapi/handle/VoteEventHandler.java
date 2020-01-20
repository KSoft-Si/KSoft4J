package net.explodingbush.ksoftapi.handle;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.events.VoteEvent;
import net.explodingbush.ksoftapi.entities.impl.VoteImpl;
import net.explodingbush.ksoftapi.hooks.IKSoftListenerManager;
import org.json.JSONObject;

public class VoteEventHandler extends Handler {

    private final IKSoftListenerManager manager = getKSoftAPI().getWebhookManager().getManager();

    public VoteEventHandler(KSoftAPI api) {
        super(api);
    }

    @Override
    public void handleInternally(JSONObject json) {

        VoteEvent voteEvent = new VoteEvent(getKSoftAPI(), new VoteImpl(json.getJSONObject("event_data")));
        manager.handle(voteEvent);
    }
}
