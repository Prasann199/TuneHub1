package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;
@Controller
public class SongController {

	@Autowired
	SongServiceimpl songserviceimpl;
	
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		
		boolean songStatus=songserviceimpl.songExists(song.getName());
		if(songStatus != true) {
			songserviceimpl.addSong(song);
			System.out.println("Song added successfully!");
		}else {
			System.out.println("Song Already Exists");
		}
		return "adminhome";
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList =songserviceimpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displaysongs";
	}
	
	@GetMapping("/rightside")
	public String rightside(Model model) {
		List<Song> songList =songserviceimpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "rightside";
	}
	
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		
		boolean premium=false;
		if(premium) {
		List<Song> songList =songserviceimpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displaysongs";
		}else {
			return "subscriptionform";
		}
	}
}
