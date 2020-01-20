package net.explodingbush.ksoftapi.entities;

public interface TaggedImage {

    /**
     * The URL of an image
     *
     * @return The corresponding URl of the image
     */
    String getUrl();

    /**
     * The Snowflake of an image
     *
     * @return The corresponding Snowflake of the image
     */
    String getSnowflake();

    /**
     * The image NSFW boolean
     *
     * @return The corresponding boolean if the image is NSFW
     */
    boolean isNsfw();

    /**
     * The tag of an image
     *
     * @return The tag of the image
     */
    String getTag();

    @Override
    String toString();


}
