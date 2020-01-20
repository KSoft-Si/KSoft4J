package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Alert;
import net.explodingbush.ksoftapi.entities.Location;
import net.explodingbush.ksoftapi.entities.Weather;
import net.explodingbush.ksoftapi.enums.PrecipType;
import net.explodingbush.ksoftapi.enums.ReportType;
import net.explodingbush.ksoftapi.enums.Units;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherImpl implements Weather {

    private JSONObject json;
    private ReportType type;

    public WeatherImpl(JSONObject json, ReportType type) {
        this.json = json.getJSONObject("data");
        this.type = type;
    }
    private WeatherImpl(JSONObject json) {
        this.json = json;
    }
    @Override
    public String getSummary() {
        return json.optString("summary");
    }
    
	@Override
	public Units getUnits() {
		try {
			return Units.valueOf(json.getString("units").toUpperCase());
		}
		catch(Exception e) {
			return Units.AUTO;
		}
	}
	
    @Override
    public String getIcon() {
        return json.optString("icon");
    }

    public String getIconUrl() {
        return json.optString("icon_url");
    }

    public List<Alert> getAlerts() {
        List<Alert> alerts = new ArrayList<>();
        for(Object o : json.optJSONArray("alerts")) {
            JSONObject obj = new JSONObject(o.toString());
            Alert alert = new AlertImpl(obj);
            alerts.add(alert);
        }
        return Collections.unmodifiableList(alerts);
    }

    public Location getLocation() {
        return new LocationImpl(json.getJSONObject("location"));
    }

    @Override
    public List<Weather> getAll() {
        List<Weather> weathers = new ArrayList<>();
        JSONArray array;
        switch(type) {
            case DAILY: array = json.optJSONArray("by_day");
            break;
            case HOURLY: array = json.optJSONArray("by_hour");
            break;
            case MINUTELY: array = json.optJSONArray("by_minute");
            break;
            case CURRENTLY: array = null;
            break;
            default: array = null;
        }
        if(array == null) {
            return weathers;
        }
        for(Object o : array) {
            JSONObject obj = new JSONObject(o.toString());
            WeatherImpl impl = new WeatherImpl(obj);
            weathers.add(impl);
        }
        return Collections.unmodifiableList(weathers);
    }

    @Override
    public OffsetDateTime getSunriseTime() {
        if(!json.has("sunsetTime")) {
            return null;
        }
        return LocalDateTime.parse(json.getString("sunsetTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public OffsetDateTime getSunsetTime() {
        if(!json.has("sunriseTime")) {
            return null;
        }
        return LocalDateTime.parse(json.getString("sunriseTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public float getMoonPhase() {
        return (float) json.optDouble("moonPhase");
    }

    @Override
    public float getPrecipIntensityMax() {
        return (float) json.optDouble("precipIntensityMax");
    }

    @Override
    public OffsetDateTime getPrecipIntensityMaxTime() {
        if(!json.has("precipIntensityMaxTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("precipIntensityMaxTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getPrecipAccumulation() {
        return (float) json.optDouble("precipAccumulation");
    }

    @Override
    public PrecipType getPrecipType() {
        if(!json.has("precipType")) {
            return null;
        }
        String type = json.getString("precipType");
        return PrecipType.valueOf(type.toUpperCase());
    }

    @Override
    public float getTemperatureHigh() {
        return (float) json.optDouble("temperatureHigh");
    }

    @Override
    public OffsetDateTime getTemperatureHighTime() {
        if(!json.has("temperatureHighTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("temperatureHighTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getTemperatureLow() {
        return (float) json.optDouble("temperatureLow");
    }

    @Override
    public OffsetDateTime getTemperatureLowTime() {
        if(!json.has("temperatureLowTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("temperatureLowTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getApparentTemperatureHigh() {
        return (float) json.optDouble("apparentTemperatureHigh");
    }

    @Override
    public OffsetDateTime getApparentTemperatureHighTime() {
        if(!json.has("apparentTemperatureHighTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("apparentTemperatureHighTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getApparentTemperatureLow() {
        return (float) json.optDouble("apparentTemperatureLowTime");
    }

    @Override
    public OffsetDateTime getApparentTemperatureLowTime() {
        if(!json.has("apparentTemperatureLowTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("apparentTemperatureLowTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getTemperatureMin() {
        return (float) json.optDouble("temperatureMin");
    }

    @Override
    public OffsetDateTime getTemperatureMinTime() {
        if(!json.has("temperatureMinTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("temperatureMinTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getApparentTemperatureMin() {
        return (float) json.optDouble("apparentTemperatureMin");
    }

    @Override
    public OffsetDateTime getApparentTemperatureMinTime() {
        if(!json.has("apparentTemperatureMinTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("apparentTemperatureMinTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getTemperatureMax() {
        return (float) json.optDouble("temperatureMax");
    }

    @Override
    public OffsetDateTime getTemperatureMaxTime() {
        if(!json.has("temperatureMaxTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("temperatureMaxTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public float getApparentTemperatureMax() {
        return (float) json.optDouble("apparentTemperatureMax");
    }

    @Override
    public OffsetDateTime getApparentTemperatureMaxTime() {
        if(!json.has("apparentTemperatureMaxTime")) {
            return null;
        }
        return Instant.ofEpochSecond(json.getLong("apparentTemperatureMaxTime")).atOffset(ZoneOffset.from(Instant.now()));
    }

    @Override
    public int getNearestStormDistance() {
        return json.getInt("nearestStormDistance");
    }

    @Override
    public int getNearestStormBearing() {
        return json.getInt("nearestStormBearing");
    }
    @Override
    public OffsetDateTime getTime() {
        return LocalDateTime.parse(json.getString("time"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public float getPrecipIntensity() {
        return (float) json.getDouble("precipIntensity");
    }

    @Override
    public float getPrecipProbability() {
        return (float) json.getDouble("precipProbability");
    }

    @Override
    public float getTemperature() {
        return (float) json.getDouble("temperature");
    }

    @Override
    public float getApparentTemperature() {
        return (float) json.getDouble("apparentTemperature");
    }

    @Override
    public float getDewPoint() {
        return (float) json.getDouble("dewPoint");
    }

    @Override
    public float getHumidity() {
        return (float) json.getDouble("humidity");
    }

    @Override
    public float getPressure() {
        return (float) json.getDouble("pressure");
    }

    @Override
    public float getWindSpeed() {
        return (float) json.getDouble("windSpeed");
    }

    @Override
    public float getWindGust() {
        return (float) json.getDouble("windGust");
    }

    @Override
    public int getWindBearing() {
        return json.getInt("windBearing");
    }

    @Override
    public float getCloudCover() {
        return (float) json.getDouble("cloudCover");
    }

    @Override
    public int getUvIndex() {
        return json.getInt("uvIndex");
    }

    @Override
    public float getVisibility() {
        return (float) json.getDouble("visibility");
    }

    @Override
    public float getOzone() {
        return (float) json.getDouble("ozone");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Weather{\n");
        json.toMap().forEach((s, o) -> builder.append(s + "=" + o + "\n"));
        builder.append("}");
        return builder.toString();
    }
}
