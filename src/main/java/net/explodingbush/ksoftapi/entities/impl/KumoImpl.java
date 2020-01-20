package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Alert;
import net.explodingbush.ksoftapi.entities.Location;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class KumoImpl {

    private JSONObject json;

    public KumoImpl(JSONObject json) {
        this.json = json;
    }

    public String getMainSummary() {
        return json.getString("summary");
    }

    public String getMainIcon() {
        return json.getString("icon");
    }

    public String getMainIconUrl() {
        return json.getString("icon_url");
    }

    public List<Alert> getAlerts() {
        List<Alert> alerts = new ArrayList<>();
        for(Object o : json.getJSONArray("alerts")) {
            JSONObject obj = new JSONObject(o.toString());
            Alert alert = new AlertImpl(obj);
            alerts.add(alert);
        }
        return Collections.unmodifiableList(alerts);
    }

    public Location getLocation() {
        return new LocationImpl(json.getJSONObject("location"));
    }
}
