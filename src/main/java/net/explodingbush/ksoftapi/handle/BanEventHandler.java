package net.explodingbush.ksoftapi.handle;

import net.explodingbush.ksoftapi.entities.impl.BanImpl;
import net.explodingbush.ksoftapi.hooks.IKSoftListenerManager;
import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.events.BanEvent;
import org.json.JSONObject;

public class BanEventHandler extends Handler {

    private final IKSoftListenerManager manager = getKSoftAPI().getWebhookManager().getManager();

    public BanEventHandler(KSoftAPI api) {
        super(api);
    }

    @Override
    public void handleInternally(JSONObject json) {
        BanEvent banEvent = new BanEvent(getKSoftAPI(), new BanImpl(json.getJSONObject("event_data")));
        manager.handle(banEvent);
    }
}
