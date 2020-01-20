package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.enums.PrecipType;
import net.explodingbush.ksoftapi.enums.Units;

import java.time.OffsetDateTime;
import java.util.List;

public interface Weather {

    List<Weather> getAll();
    OffsetDateTime getTime();
    String getSummary();
    float getTemperature();
    float getApparentTemperature();
    Units getUnits();
    String getIcon();
    String getIconUrl();
    OffsetDateTime getSunriseTime();
    OffsetDateTime getSunsetTime();
    float getMoonPhase();
    float getPrecipIntensity();
    float getPrecipIntensityMax();
    OffsetDateTime getPrecipIntensityMaxTime();
    float getPrecipProbability();
    float getPrecipAccumulation();
    PrecipType getPrecipType();
    float getTemperatureHigh();
    OffsetDateTime getTemperatureHighTime();
    float getTemperatureLow();
    OffsetDateTime getTemperatureLowTime();
    float getApparentTemperatureHigh();
    OffsetDateTime getApparentTemperatureHighTime();
    float getApparentTemperatureLow();
    OffsetDateTime getApparentTemperatureLowTime();
    float getTemperatureMin();
    OffsetDateTime getTemperatureMinTime();
    float getApparentTemperatureMin();
    OffsetDateTime getApparentTemperatureMinTime();
    float getTemperatureMax();
    OffsetDateTime getTemperatureMaxTime();
    float getApparentTemperatureMax();
    OffsetDateTime getApparentTemperatureMaxTime();
    float getDewPoint();
    float getHumidity();
    float getPressure();
    int getNearestStormDistance();
    int getNearestStormBearing();
    float getWindSpeed();
    float getWindGust();
    int getWindBearing();
    float getCloudCover();
    int getUvIndex();
    float getVisibility();
    float getOzone();
    List<Alert> getAlerts();
    Location getLocation();

    @Override
    String toString();

}
