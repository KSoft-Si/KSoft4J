package net.explodingbush.ksoftapi.webhooks;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.exceptions.InvalidPortException;
import net.explodingbush.ksoftapi.hooks.IKSoftListenerManager;
import net.explodingbush.ksoftapi.hooks.InterfacedKSoftEventManager;
import net.explodingbush.ksoftapi.utils.Checks;
//import org.json.JSONObject;

public class WebhookManager {

    private static String token;
    private static int listenPort;
    private final KSoftAPI api;
    private IKSoftListenerManager eventManager = new InterfacedKSoftEventManager();

    public WebhookManager(KSoftAPI api) {
        this.api = api;
    }
    public KSoftAPI getKSoftAPI() {
        return api;
    }
    public WebhookManager setToken(String apiToken) {
        Checks.notNull(apiToken, "Webhook Token");
        token = apiToken;
        return this;
    }
    public WebhookManager addEventListener(Object... listeners) {
        Checks.notNull(listeners, "Listeners");

        for(Object listener : listeners) {
            eventManager.register(listener);
        }
        return this;
    }
    public WebhookManager removeEventListener(Object... listeners) {
        Checks.notNull(listeners, "Listeners");

        for (Object listener : listeners) {
            eventManager.unregister(listener);
        }
        return this;
    }
    public IKSoftListenerManager getManager() {
        return eventManager;
    }
    public void start() {
        Thread service = new Thread(new WebhookService(api).runnable);
        service.setName("WebhookService");
        service.start();
    }
    public String getToken() {
        return token;
    }

    public WebhookManager setPort(int port) {
        if(port >= 1024) {
            listenPort = port;
        } else {
            throw new InvalidPortException();
        }
        return this;
    }

    public int getPort() {
        //JSONObject json = new JSONObject(); // Why the heck is this here
        return listenPort;
    }
}
