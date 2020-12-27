package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String place;
	private int availablePlaces;
	private Date time;
	@ManyToMany(mappedBy = "events",fetch = FetchType.LAZY)
	private List<Person> persons =new ArrayList<>();
	@OneToOne
	@JoinColumn( name = "host", referencedColumnName = "id")
	private Team host;
	@OneToOne()
	@JoinColumn(name ="guest",referencedColumnName = "id")
	private Team guest;
	public Event() {
	}
	public Event(String place, Date time) {
		this.place = place;
		this.time = time;
		this.persons=new ArrayList<Person>();
	}
	public Event(long id,String place, Date time) {
		this.id=id;
		this.place = place;
		this.time = time;
		this.persons=new ArrayList<Person>();
	}
	public Event(long id,String place, Date time,int availablePlaces) {
		this.id=id;
		this.place = place;
		this.time = time;
		this.availablePlaces= availablePlaces;
		this.persons=new ArrayList<Person>();
	}
	public Event(String place, Date time,int availablePlaces, Team host, Team guest) {
		this.place = place;
		this.time = time;
		this.host = host;
		this.guest = guest;
		this.persons=new ArrayList<Person>();
	}

	public Event(Long id, String place,int availablePlaces, Date time, Team host, Team guest) {
		this.id = id;
		this.place = place;
		this.time = time;
		this.host = host;
		this.guest = guest;
		this.persons=new ArrayList<Person>();
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getAvailablePLaces() {
		return availablePlaces;
	}
	public void setAvailablePLaces(int availablePLaces) {
		this.availablePlaces = availablePLaces;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public Long getId() {
		return id;
	}
	public Team getHost() {
		return host;
	}
	public void setHost(Team host) {
		this.host = host;
	}
	public Team getGuest() {
		return guest;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", place=" + place + ", time=" + time + "," + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	
}
