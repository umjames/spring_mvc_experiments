package com.mapleglensoftware.experiments.songstoget.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mapleglensoftware.experiments.songstoget.model.Song;
import com.mapleglensoftware.experiments.songstoget.persistence.SongsDAO;

@Controller
@RequestMapping(value="/songs")
public class SongsController
{
	private SongsDAO	songsDAO;

	@Autowired
	public void setSongsDAO(SongsDAO dao)
	{
		songsDAO = dao;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list()
	{
		List<Song>	songs = songsDAO.getAll();
		return new ModelAndView("song_list", "songs", songs);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView showAddSongForm()
	{
		return new ModelAndView("song_form");
	}
}
