package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.Recommendation;
import net.explodingbush.ksoftapi.enums.RecommendationProvider;
import net.explodingbush.ksoftapi.entities.impl.RecommendationImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.AlreadyExistsException;
import net.explodingbush.ksoftapi.exceptions.FullException;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendationAction extends KSoftActionAdapter<Recommendation> {

    private String token;
    private ArrayList<String> tracks = new ArrayList<>();
    private RecommendationProvider provider;


    public RecommendationAction(String token) {
        this.token = token;
    }

    /**
     * Declare the provider to add tracks with
     *
     * @param provider
     * The provider to add tracks with
     *
     * @return The RecommendationAction instance. Useful for chaining.
     */
    public RecommendationAction setProvider(RecommendationProvider provider) {
        this.provider = provider;
        return this;
    }
    /**
     * Add a track to the array of tracks to recommendations for.
     *
     * <p>Make sure you are entering the information! This will not work if you are using the wrong RecommendationProvider</p>
     *
     * @param track
     * The track to add to the array
     *
     * @throws FullException
     * If you have more than five tracks in the array
     *
     * @throws AlreadyExistsException
     * If the track you're inputting already exists in the array
     *
     * @return The RecommendationAction instance. Useful for chaining.
     */
    public RecommendationAction addTrack(String track) {
        if(tracks.contains(track)) {
            throw new AlreadyExistsException(track + " already exists in the array");
        }
        if(tracks.size() >= 5) {
            throw new FullException("You can only have a maximum of five tracks in a recommendation");
        }
        tracks.add(track);
        return this;
    }

    /**
     * Adds tracks to the array of tracks to get recommendations for.
     *
     * <p>Make sure you are entering the information! This will not work if you are using the wrong RecommendationProvider</p>
     *
     * @param track
     * The track to add to the array
     *
     * @throws FullException
     * If you have more than five tracks in the array
     *
     * @throws AlreadyExistsException
     * If the track you're inputting already exists in the array
     *
     * @return The RecommendationAction instance. Useful for chaining.
     */
    public RecommendationAction addTracks(String... track) {
        for(String s : track) {
            if(tracks.contains(s)) {
                throw new AlreadyExistsException(s + " already exists in the array");
            }
            if(tracks.size() >= 5) {
                throw new FullException("You can only have a maximum of five tracks in a recommendation");
            }
            tracks.add(s);
        }
        return this;
    }
    /**
     * Executes the provided request synchronously.
     *
     * <p><b>This method is synchronous</b></p>
     *
     * @return A not-null {@link Recommendation Recommendation} instance.
     * @throws LoginException           If the token is not provided or incorrect.
     */
    @Override
    public Recommendation execute() {
        Checks.notNull(provider, "provider");
        JSONObject data = new JSONObject();
        JSONArray tracksArray = new JSONArray();
        tracks.forEach(tracksArray::put);
        data.put("tracks", tracksArray).put("provider", provider.getType());
        Response response = new JSONBuilder().recommendSongRequest(Routes.MUSIC_RECOMMEND, data, token);
        if(response.code() == 500) {
            throw new NotFoundException("NANI broke something. Everything is currently being exploded");
        }
        JSONObject json = new JSONBuilder().getJSONResponse(response);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        if(response.code() == 403 && json.getBoolean("error")) {
            throw new LoginException("You need a premium plan to use music recommendations");
        }
        if(response.code() == 400 && json.getBoolean("error")) {
            throw new NotFoundException("You need to have at least one track in the array");
        }
        return new RecommendationImpl(json);
    }
}
