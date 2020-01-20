package net.explodingbush.ksoftapi.entities;

public interface Location {

    /**
     * The latitude coordinates of a weather location
     *
     * @return The corresponding latitude coordinates of a weather location
     */
    double getLatitude();
    /**
     * The longitude coordinates of a weather location
     *
     * @return The corresponding longitude coordinates of a weather location
     */
    double getLongitude();

    /**
     * The address of a weather location
     *
     * @return The corresponding address of a weather location
     */
    String getAddress();

    @Override
    String toString();

}
