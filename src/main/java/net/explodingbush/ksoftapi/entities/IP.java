package net.explodingbush.ksoftapi.entities;

import java.util.TimeZone;

public interface IP {

    /**
     * The city an IP address resides in
     *
     * @return The corresponding city the IP address resides in
     */
    String getCity();

    /**
     * The continent code an IP address resides in
     *
     * @return The corresponding continent code the IP address resides in
     */
    String getContinentCode();

    /**
     * The continent an IP address resides in
     *
     * @return The corresponding continent the IP address resides in
     */
    String getContinentName();

    /**
     * The country code an IP address resides in
     *
     * @return The corresponding country code the IP address resides in
     */
    String getCountryCode();

    /**
     * The country an IP address resides in
     *
     * @return The corresponding country the IP address resides in
     */
    String getCountryName();

    /**
     * The latitude coordinates an IP address resides in
     *
     * @return The corresponding latitude coordinates the IP address resides in
     */
    double getLatitude();

    /**
     * The longitude coordinates an IP address resides in
     *
     * @return The corresponding longitude coordinates the IP address resides in
     */
    double getLongitude();

    /**
     * The postal/ZIP code an IP address resides in
     *
     * @return The corresponding postal/ZIP code the IP address resides in
     */
    String getPostalCode();

    /**
     * The region/town an IP address resides in
     *
     * @return The corresponding region/town the IP address resides in
     */
    String getRegion();

    /**
     * The timezone object an IP address resides in
     *
     * @return The corresponding timezone object the IP address resides in
     */
    TimeZone getTimeZone();

    /**
     * A boolean if an IP address exists
     *
     * @return The corresponding boolean the IP address exists
     */
    boolean exists();

    @Override
    String toString();

}
