package net.explodingbush.ksoftapi.entities;

import java.time.OffsetDateTime;
import java.util.List;

public interface Alert {

    /**
     * The title of a weather alert
     *
     * @return The corresponding title for the weather alert
     */
    String getTitle();

    /**
     * Gets a list of the affected regions
     *
     * @return An immutable list of regions
     */
    List<String> getRegions();

    /**
     * The serverity of a weather alert
     *
     * @return The corresponding title for the weather alert
     */
    String getSeverity();

    /**
     * The time an alert was created
     *
     * @return The corresponding time the alert was created
     */
    OffsetDateTime getTime();

    /**
     * The time an alert will expire
     *
     * @return The corresponding time the alert will expire
     */
    OffsetDateTime getExpiryTime();

    /**
     * The description of a weather alert
     *
     * @return The corresponding description of the weather alert
     */
    String getDescription();

    /**
     * The URL depending of the alert from a government agency relative to your location
     *
     * @return The corresponding URL rom a government agency relative to your location
     */
    String getURL();

    @Override
    String toString();

}
