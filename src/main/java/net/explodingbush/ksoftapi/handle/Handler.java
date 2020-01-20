package net.explodingbush.ksoftapi.handle;

import net.explodingbush.ksoftapi.KSoftAPI;
import org.json.JSONObject;

public abstract class Handler {

    private final KSoftAPI api;

    public Handler(KSoftAPI api) {
        this.api = api;
    }

    public abstract void handleInternally(JSONObject content);

    protected KSoftAPI getKSoftAPI() {
        return api;
    }


}
