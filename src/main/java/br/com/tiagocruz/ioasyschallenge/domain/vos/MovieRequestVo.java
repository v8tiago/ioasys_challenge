package br.com.tiagocruz.ioasyschallenge.domain.vos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("MovieRequest")
public class MovieRequestVo implements Serializable {

	private static final long serialVersionUID = -488025483674809043L;

	@NotBlank
	@ApiModelProperty(value = "The name of the movie", required = true)
	private String name;

	@NotNull
	@ApiModelProperty("The name of the director of the movie")
	private String directorName;

	@NotNull
	@ApiModelProperty("The genre of the movie")
	private String genre;

	@NotNull
	@ApiModelProperty("The list of the actors who played the movie")
	private List<String> actors;

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
