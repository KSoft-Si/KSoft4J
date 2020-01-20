package net.explodingbush.ksoftapi.entities.actions;

public class MusicAction {

    private String token;


    public MusicAction(String token) {
        this.token = token;
    }

    /**
     * Gets a recommendation action
     *
     * @return A new {@link RecommendationAction RecommendationAction} instance.
     */
    public RecommendationAction getRecommendationAction() {
        return new RecommendationAction(token);
    }
}
