package com.mapleglensoftware.experiments.songstoget.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mapleglensoftware.experiments.songstoget.model.Song;
import com.mapleglensoftware.experiments.songstoget.persistence.SongsDAO;

@Controller
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
		List<Song>			songs = songsDAO.getAll();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("songs", songs);
		modelMap.put("page_title", "Song List");
		modelMap.put("date_tool", new DateTool());
		
		return new ModelAndView("song_list", modelMap);
	}
	
	@RequestMapping(value="/song/{song_id}/edit", method=RequestMethod.GET)
	public ModelAndView editSong(@PathVariable("song_id") int songID)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Song				song = songsDAO.findByID(songID);
		
		modelMap.put("page_title", "Update Song");
		modelMap.put("song", song);
		
		return new ModelAndView("song_form", modelMap);
	}
	
	@RequestMapping(value="/songs/new", method=RequestMethod.GET)
	public ModelAndView showAddSongForm()
	{
		Map<String, Object>	modelMap = new HashMap<String, Object>();
		
		modelMap.put("page_title", "Add Song");
		modelMap.put("song", new Song());
		
		return new ModelAndView("song_form", modelMap);
	}
	
	@RequestMapping(value="/songs/new", method=RequestMethod.POST)
	public ModelAndView addSong(@Valid Song song, BindingResult errors)
	{
		Map<String, Object>	model = new HashMap<String, Object>();
		
		if (errors.hasErrors())
		{
			model.put("page_title", "Add Song");
			model.put("song", song);
			return new ModelAndView("song_form", model);
		}
		
		songsDAO.save(song);
		return new ModelAndView("redirect:/songs");
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder)
//	{
//		SimpleDateFormat	dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
}
