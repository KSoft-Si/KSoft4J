package net.explodingbush.ksoftapi.enums;

public enum RecommendationProvider {
    /**
     * The alternative to <b>YOUTUBE_URLS</b> on KSoft would be <b>youtube</b>
     */
    YOUTUBE_URLS,

    /**
     * The alternative to <b>YOUTUBE_IDS</b> on KSoft would be <b>youtube_ids</b>
     */
    YOUTUBE_IDS,

    /**
     * The alternative to <b>SPOTIFY_IDS</b> on KSoft would be <b>spotify</b>
     */
    SPOTIFY_IDS;

    /**
     * Converts the enum to the KSoft alternative. Mostly used for executing the request
     * @return The KSoft alternative
     */
    public String getType() {
        String provider = toString();
        switch (provider) {
            case "YOUTUBE_URLS": return "youtube";
            case "YOUTUBE_IDS": return "youtube_ids";
            case "SPOTIFY": return "spotify";
            default: return null;
        }
    }
}
