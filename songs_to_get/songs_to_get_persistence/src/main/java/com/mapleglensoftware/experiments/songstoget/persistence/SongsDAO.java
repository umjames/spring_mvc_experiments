package com.mapleglensoftware.experiments.songstoget.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mapleglensoftware.experiments.songstoget.model.Song;

public class SongsDAO extends HibernateDaoSupport
{
	public SongsDAO()
	{
	}
	
	public Song findByID(final int songID)
	{
		return getHibernateTemplate().execute(new HibernateCallback<Song>() {
			@Override
			public Song doInHibernate(Session session) throws HibernateException, SQLException
			{
				return (Song)session.get(Song.class, songID);
			}
		});
	}
	
	public void delete(final Song song)
	{
		getHibernateTemplate().execute(new HibernateCallback<Song>() {

			@Override
			public Song doInHibernate(Session session) throws HibernateException, SQLException
			{
				session.delete(song);
				return null;
			}
			
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Song> getAll()
	{
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Song>>() {
			@Override
			public List<Song> doInHibernate(Session session) throws HibernateException,	SQLException
			{
				Query	query = session.createQuery("from Song s order by s.dateUpdated desc");
				return query.list();
			}
		});
	}
	
	public void save(final Song song)
	{
		getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{
				Date	insertionDate = new Date();
				
				if (null == song.getId())
				{
					song.setDateAdded(insertionDate);
				}
				
				song.setDateUpdated(insertionDate);
				session.saveOrUpdate(song);
				return null;
			}
			
		});
	}
}
