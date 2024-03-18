package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.PlaylistServiceimpl;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;

@Controller
public class PlaylistController {

	@Autowired
	SongServiceimpl songserviceimpl;

	@Autowired
	PlaylistServiceimpl playlistServiceimpl;
	
	@GetMapping("/createplaylists")
	public String createPlaylists(Model model) {
		List<Song> songList =songserviceimpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylists";
	}
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		playlistServiceimpl.addPlaylist(playlist);
		playlist.setName(playlist.getName());
		System.out.println(playlist.getName());
		List<Song> songList = playlist.getSongs();
		for(Song s: songList) {
			s.getPlaylists().add(playlist);
			songserviceimpl.updateSong(s);
		}
		return "adminhome";
	}
	@GetMapping("/viewplaylists")
	public String viewPlaylists(Model model){
		List<Playlist> playlist=playlistServiceimpl.fetchAllPlaylist();
		model.addAttribute("list", playlist);
		return "displayplaylists";
	}
	@GetMapping("/viewplaylists1")
	public String displayplaylists(Model model){
		List<Playlist> allplaylist=playlistServiceimpl.fetchAllPlaylist();
		model.addAttribute("list", allplaylist);
		return "viewplaylists1";
	}
}
