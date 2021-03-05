package br.com.tiagocruz.ioasyschallenge.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE")
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = -4504849428080385265L;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "director_name")
	private String directorName;

	@Column(name = "genre")
	private String genre;

	@ElementCollection()
	@CollectionTable(name = "MOVIE_ACTORS", joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	@Column(name = "actor")
	private List<String> actors;

	public Integer getId() {

		return id;
	}

	public void setId(final Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getDirectorName() {

		return directorName;
	}

	public void setDirectorName(final String directorName) {

		this.directorName = directorName;
	}

	public String getGenre() {

		return genre;
	}

	public void setGenre(final String genre) {

		this.genre = genre;
	}

	public List<String> getActors() {

		return actors;
	}

	public void setActors(final List<String> actors) {

		this.actors = actors;
	}
}
