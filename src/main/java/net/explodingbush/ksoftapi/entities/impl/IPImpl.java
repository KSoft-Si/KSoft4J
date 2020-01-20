package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.IP;
import org.json.JSONObject;

import java.util.TimeZone;

public class IPImpl implements IP {

    private JSONObject json;
    private JSONObject data;

    public IPImpl(JSONObject json) {
        this.json = json;
        this.data = json.getJSONObject("data");
    }

    @Override
    public String getCity() {
        return data.optString("city");
    }

    @Override
    public String getContinentCode() {
        return data.optString("continent_code");
    }

    @Override
    public String getContinentName() {
        return data.optString("continent_name");
    }

    @Override
    public String getCountryCode() {
        return data.optString("country_code");
    }

    @Override
    public String getCountryName() {
        return data.optString("country_name");
    }

    @Override
    public double getLatitude() {
        return data.optDouble("latitude");
    }

    @Override
    public TimeZone getTimeZone() {
        return TimeZone.getTimeZone(data.optString("time_zone"));
    }

    @Override
    public double getLongitude() {
        return data.optDouble("longitude");
    }

    @Override
    public String getPostalCode() {
        return data.optString("postal_code");
    }

    @Override
    public String getRegion() {
        return data.optString("region");
    }
    @Override
    public boolean exists() {
       return json.optInt("code") == 200;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IP{\n");
        json.toMap().forEach((s, o) -> {
            if(!s.equalsIgnoreCase("data")) {
                builder.append(s + "=" + o + "\n");
            }
        });
        builder.append("data={\n");
        data.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}\n}");
        return builder.toString();
    }
}
