package net.explodingbush.ksoftapi.entities;

public interface SpotifyAlbum {

    /**
     * The name of a Spotify album
     *
     * @return The corresponding name of the Spotify album
     */
    String getName();

    /**
     * The album art of a Spotify album
     *
     * @return The corresponding art of the Spotify album
     */
    String getAlbumArtUrl();

    /**
     * The Spotify album URL
     *
     * @return The corresponding link of the Spotify album
     */
    String getLink();

    @Override
    String toString();
}
