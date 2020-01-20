package net.explodingbush.ksoftapi.entities;
import net.explodingbush.ksoftapi.enums.RecommendationProvider;

import java.util.List;

public interface Recommendation {

    /**
     * The provider used to get track recommendations
     *
     * @return The corresponding provider used to get track recommendations
     */
    RecommendationProvider getProvider();

    /**
     * A total amount of tracks recommended
     *
     * @return The corresponding amount of tracks recommended
     */
    int getTotal();

    /**
     * Gets a list of recommended tracks
     *
     * @return An immutable list of recommended tracks
     */
    List<RecommendationTrack> getRecommendationTracks();

    @Override
    String toString();

}
