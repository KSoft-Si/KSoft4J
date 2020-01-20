package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.Lyric;
import net.explodingbush.ksoftapi.entities.lyrics.LyricCache;
import net.explodingbush.ksoftapi.entities.lyrics.Track;
import net.explodingbush.ksoftapi.entities.lyrics.impl.TrackImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LyricAction extends KSoftActionAdapter<List<Track>> {

	private int limit;
	private String query;
	private String token;
	private boolean textOnly;
	
	private LyricCache cache;
	
	public LyricAction(String token, LyricCache cache){
		Checks.notNull(token, "token");
		this.cache = cache;
		this.token = token;
		this.limit = 10;
	}

	/**
	 * Declare the search query to find lyrics with
	 *
	 * @param query
	 * The query to find lyrics with
	 *
	 * @return The LyricAction instance. Useful for chaining.
	 */
	public LyricAction search(String query){
		Checks.notBlank(query, "query");
		this.query = query;
		return this;
	}
	/**
	 * Declare if KSoft4J should search inside the lyrics rather the song and artist information
	 *
	 * <p>Default: <b>false (no)</b></p>
	 * @param textOnly
	 * True - Search within the lyrics
	 * @return The LyricAction instance. Useful for chaining.
	 */
	public LyricAction searchLyricTextOnly(boolean textOnly) {
		this.textOnly = textOnly;
		return this;
	}

	/**
	 * Declare the amount of results KSoft4J should return
	 *
	 * <p>Default: <b>10</b></p>
	 * @param limit
	 * The amount of results to return
	 *
	 * @return The LyricAction instance. Useful for chaining.
	 */
	public LyricAction setLimit(int limit) {
		Checks.positive(limit, "limit");
		this.limit = limit;
		return this;
	}
	/**
	 * Executes the provided request synchronously.
	 *
	 * <p><b>This method is synchronous</b></p>
	 *
	 * If there are no lyrics found, this will return an empty array.
	 *
	 * @return A possibly-empty list of {@link Lyric Lyrics}
	 * @throws LoginException If the token is not provided or incorrect.
	 */
	@Override
	public List<Track> execute() {
		JSONObject json;
		json = new JSONBuilder().requestKsoft(String.format(Routes.LYRICS_SEARCH.toString(), query, Boolean.toString(textOnly), limit), token);
        if (json.getInt("total") == 0) {
        	return Collections.emptyList();
        }
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        List<Track> lyrics = new ArrayList<>();
        json.getJSONArray("data").forEach(obj -> lyrics.add(new TrackImpl((JSONObject)obj, cache)));
		return Collections.unmodifiableList(lyrics);
	}
}
