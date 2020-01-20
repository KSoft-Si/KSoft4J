package net.explodingbush.ksoftapi.entities.lyrics;

import java.util.List;

public interface Artist {

	String getName();
	List<Album> getAlbums();
	List<Track> getTracks();
	int getId();
}
