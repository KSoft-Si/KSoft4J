package net.explodingbush.ksoftapi.entities.lyrics;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.concurrent.ConcurrentHashMap;

public class LyricCache {

	private ConcurrentHashMap<Integer, Album> albums;
	private ConcurrentHashMap<Integer, Track> tracks;
	private ConcurrentHashMap<Integer, Artist> artists;
	
	private String token;
	
	public LyricCache(String token) {
		this.token = token;
		this.albums = new ConcurrentHashMap<>();
		this.tracks = new ConcurrentHashMap<>();
		this.artists = new ConcurrentHashMap<>();
	}
	protected String getToken() {
		return token;
	}
	public Collection<Album> getAlbumCache(){
		return Collections.unmodifiableCollection(albums.values());
	}
	public Collection<Track> getTrackCache(){
		return Collections.unmodifiableCollection(tracks.values());
	}
	public Collection<Artist> getArtistCache(){
		return Collections.unmodifiableCollection(artists.values());
	}
	public Map<Integer, Album> getAlbumMap(){
		return Collections.unmodifiableMap(albums);
	}
	public Map<Integer, Track> getTrackMap(){
		return Collections.unmodifiableMap(tracks);
	}
	public Map<Integer, Artist> getArtistMap(){
		return Collections.unmodifiableMap(artists);
	}
	public Album getAlbumById(int id) {
		return albums.get(id);
	}
	public Track getTrackById(int id) {
		return tracks.get(id);
	}
	public Artist getArtistById(int id) {
		return artists.get(id);
	}
	public List<Album> getAlbumsByName(String name){
		return albums.values().stream().filter((album) -> album.getName().equals(name)).collect(Collectors.toList());
	}
	public List<Track> getTracksByName(String name){
		return tracks.values().stream().filter((track) -> track.getName().equals(name) || track.getFullName().equals(name)).collect(Collectors.toList());
	}
	public List<Artist> getArtistsByName(String name){
		return artists.values().stream().filter((artist) -> artist.getName().equals(name)).collect(Collectors.toList());
	}
	public void addAlbum(Album album) {
		//System.out.println(album);
		albums.put(album.getId(), album);
	}
	public void addTrack(Track track) {
		//System.out.println(track);
		tracks.put(track.getId(), track);
	}
	public void addArtist(Artist artist) {
		//System.out.println(artist);
		artists.put(artist.getId(), artist);
	}
}
