package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;
@Service
public class SongServiceimpl implements SongService{

	@Autowired
	SongRepository songrepository;

	public String addSong(Song song) {
		songrepository.save(song);
		return "Song saved";
	}

	public boolean songExists(String name) {
		Song song = songrepository.findByName(name);
		if(song==null) {
			return false;
		}else {
			return true;
		}
	}

	public List<Song> fetchAllSongs() {
		 List<Song> allSong = songrepository.findAll();
		 return allSong;
		
	}

	public void updateSong(Song s) {
		songrepository.save(s);
	}
}
