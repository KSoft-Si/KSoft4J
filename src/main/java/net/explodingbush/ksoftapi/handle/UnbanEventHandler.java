package net.explodingbush.ksoftapi.handle;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.events.UnbanEvent;
import net.explodingbush.ksoftapi.entities.impl.BanImpl;
import net.explodingbush.ksoftapi.hooks.IKSoftListenerManager;
import org.json.JSONObject;

public class UnbanEventHandler extends Handler {

        private final IKSoftListenerManager manager = getKSoftAPI().getWebhookManager().getManager();

        public UnbanEventHandler(KSoftAPI api) {
            super(api);
        }

        @Override
        public void handleInternally(JSONObject json) {
            UnbanEvent unbanEvent = new UnbanEvent(getKSoftAPI(), new BanImpl(json.getJSONObject("event_data")));
            manager.handle(unbanEvent);
        }


}
