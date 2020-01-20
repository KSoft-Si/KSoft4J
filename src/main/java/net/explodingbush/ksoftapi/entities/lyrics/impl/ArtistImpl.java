package net.explodingbush.ksoftapi.entities.lyrics.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.entities.lyrics.Album;
import net.explodingbush.ksoftapi.entities.lyrics.Artist;
import net.explodingbush.ksoftapi.entities.lyrics.LyricCache;
import net.explodingbush.ksoftapi.entities.lyrics.Track;

public class ArtistImpl implements Artist {

	private JSONObject json;
	private LyricCache cache;
	
	public ArtistImpl(JSONObject json, LyricCache cache) {
		this.json = json;
		this.cache = cache;
		cache.addArtist(this);
	}

	@Override
	public String getName() {
		return json.getString("name");
	}

	@Override
	public List<Album> getAlbums() {
		Collection<Album> albumCache = cache.getAlbumCache();
		List<Album> albums = new ArrayList<>();
		for(Album album : albumCache) {
			if(album.getArtist().equals(this)) {
				albums.add(album);
			}
		}
		return albums;
	}

	@Override
	public List<Track> getTracks() {
		Collection<Track> trackCache = cache.getTrackCache();
		List<Track> tracks = new ArrayList<>();
		for(Track track : trackCache) {
			if(track.getArtist().equals(this)) {
				tracks.add(track);
			}
		}
		return tracks;
	}

	@Override
	public int getId() {
		return json.getInt("id");
	}
	
	public String toString() {
		return String.format("%s: %s (ID: %s)", this.getClass().getSimpleName(), this.getName(), this.getId());
	}
}
