package net.explodingbush.ksoftapi.entities.lyrics.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.explodingbush.ksoftapi.entities.lyrics.Album;
import net.explodingbush.ksoftapi.entities.lyrics.Artist;
import net.explodingbush.ksoftapi.entities.lyrics.LyricCache;
import net.explodingbush.ksoftapi.entities.lyrics.LyricUtil;
import net.explodingbush.ksoftapi.entities.lyrics.Track;

public class TrackImpl implements Track {

	private JSONObject json;
	private LyricCache cache;
	private boolean fromLyrics;
	
	public TrackImpl(JSONObject json, LyricCache cache) {
		this.fromLyrics = json.has("artist_id");
		this.json = json;
		this.cache = cache;
		cache.addTrack(this);
	}
	@Override
	public List<Album> getAlbums() {
		String[] albumStr;
		if(fromLyrics) {
			albumStr = json.getString("album_ids").split(",");
		}
		else {
			JSONArray arr = json.getJSONArray("album_ids");
			albumStr = new String[arr.length()];
			for(int i = 0; i < albumStr.length; i++) {
				albumStr[0] = arr.getJSONObject(i).get("id").toString();
			}
		}
		List<Album> albums = new ArrayList<>();
		for(String a : albumStr) {
			if(cache.getAlbumById(Integer.parseInt(a)) == null) {
				try {
					cache.addAlbum(LyricUtil.retrieveAlbumById(cache, Integer.parseInt(a)));
					albums.add(cache.getAlbumById(Integer.parseInt(a)));
				}
				catch(Exception e) {}
			}
		}
		return albums;
	}

	@Override
	public String getLyrics() {
		if(json.has("lyrics")) {
			return json.getString("lyrics");
		}
		json.put("lyrics", LyricUtil.retrieveTrackById(cache, getId()));
		return json.getString("lyrics");
	}

	@Override
	public Artist getArtist() {
		try {
			if(fromLyrics) {
				if(cache.getArtistById(json.getInt("artist_id")) == null) {
					cache.addArtist(LyricUtil.retrieveArtistById(cache, json.getInt("artist_id")));
				}
			}
			else {
				if(cache.getArtistById(json.getJSONObject("artist").getInt("id")) == null) {
					cache.addArtist(LyricUtil.retrieveArtistById(cache, json.getJSONObject("artist").getInt("id")));
				}
			}
			return cache.getArtistById(json.getInt(fromLyrics ? "artist_id" : "artist"));
		}
		catch(Exception e) {
			JSONObject fakeArtist = new JSONObject()
					.put("name", json.getString("artist"))
					.put("tracks", Arrays.asList(json))
					.put("albums", this.getAlbums())
					.put("id", -1);
			return new ArtistImpl(fakeArtist, cache);
		}
	}

	@Override
	public String getName() {
		return json.getString("name");
	}

	@Override
	public String getFullName() {
		return fromLyrics ? json.getString("search_str") : null;
	}

	@Override
	public int getId() {
		return Integer.parseInt(json.getString("id"));
	}
	
	@Override
	public String getAlbumArt() {
		return json.optString("album_art", null);
	}
	public String toString() {
		return String.format("%s: %s (ID: %s)", this.getClass().getSimpleName(), this.getName(), this.getId());
	}
}
