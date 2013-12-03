package com.mapleglensoftware.experiments.songstoget.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Song
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Title cannot be blank")
	@Size(min=1, max=Integer.MAX_VALUE, message="Title must have at least 1 character")
	private String	title;
	private Date	releaseDate;
	private String	artist;
	
	private Date	dateAdded;
	private Date	dateUpdated;
	
	public Song()
	{
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getReleaseDate()
	{
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public Date getDateAdded()
	{
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	public Date getDateUpdated()
	{
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated)
	{
		this.dateUpdated = dateUpdated;
	}
}
