package net.explodingbush.ksoftapi.entities;

public interface YouTubeTrack {

    /**
     * The ID of a YouTube track
     *
     * @return The corresponding ID of the YouTube track
     */
    String getId();

    /**
     * The YouTube track URL
     *
     * @return The corresponding URL of the YouTube track
     */
    String getLink();

    /**
     * The title of a YouTube track
     *
     * @return The corresponding title of the YouTube track
     */
    String getTitle();

    /**
     * The album art of a YouTube track
     *
     * @return The corresponding art of the YouTube track
     */
    String getThumbnailUrl();

    /**
     * The description of a YouTube track
     *
     * @return The corresponding description of the YouTube track
     */
    String getDescription();

    @Override
    String toString();

}
