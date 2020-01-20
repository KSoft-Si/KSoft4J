package net.explodingbush.ksoftapi.entities.lyrics;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.entities.lyrics.impl.*;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.utils.JSONBuilder;

public class LyricUtil {

	public static Artist retrieveArtistById(LyricCache cache, int id) {
		return new ArtistImpl(request(Routes.LYRICS_ARTIST, id, cache.getToken()), cache);
	}
	public static Track retrieveTrackById(LyricCache cache, int id) {
		return new TrackImpl(request(Routes.LYRICS_TRACK, id, cache.getToken()), cache);
	}
	public static Album retrieveAlbumById(LyricCache cache, int id) {
		return new AlbumImpl(request(Routes.LYRICS_ALBUM, id, cache.getToken()), cache);
	}
	private static JSONObject request(Routes route, int id, String token) {
		return new JSONBuilder().requestKsoft(String.format(route.toString(), id), token);
	}
}
