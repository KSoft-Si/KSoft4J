package net.explodingbush.ksoftapi.entities;

public interface SpotifyArtist {

    /**
     * The name of a Spotify artist
     *
     * @return The corresponding name of the Spotify track
     */
    String getName();

    /**
     * The Spotify artist URL
     *
     * @return The corresponding URL of the Spotify artist
     */
    String getLink();

    @Override
    String toString();

}
