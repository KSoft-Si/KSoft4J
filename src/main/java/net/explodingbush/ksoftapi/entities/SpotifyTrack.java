package net.explodingbush.ksoftapi.entities;
import java.util.List;

public interface SpotifyTrack {

    /**
     * The ID of a Spotify track
     *
     * @return The corresponding ID of the Spotify track
     */
    String getSongId();

    /**
     * The title of a Spotify track
     *
     * @return The corresponding title of the Spotify track
     */
    String getSongTitle();

    /**
     * The Spotify track URL
     *
     * @return The corresponding URL of the Spotify track
     */
    String getLink();

    /**
     * The Spotify album a track resides in
     *
     * @return The corresponding album a Spotify track resides in
     */
    SpotifyAlbum getAlbum();

    /**
     * Gets a list of artists that composed a Spotify track
     *
     * @return An immutable list of artists that composed the Spotify track
     */
    List<SpotifyArtist> getArtists();

}
