package net.explodingbush.ksoftapi.entities.lyrics;

import java.util.List;

public interface Track {

	List<Album> getAlbums();
	String getAlbumArt();
	String getLyrics();
	Artist getArtist();
	String getName();
	String getFullName();
	int getId();
}
