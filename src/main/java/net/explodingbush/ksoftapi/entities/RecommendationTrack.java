package net.explodingbush.ksoftapi.entities;
public interface RecommendationTrack {

    /**
     * The recommended track found on YouTube
     *
     * @return The corresponding track recommended found on YouTube
     */
    YouTubeTrack getYouTube();

    /**
     * The recommended track found on Spotify
     *
     * @return The corresponding track recommended found on Spotify
     */
    SpotifyTrack getSpotify();

    @Override
    String toString();

}
