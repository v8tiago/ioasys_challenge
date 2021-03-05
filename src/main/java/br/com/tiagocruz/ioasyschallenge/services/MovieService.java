package br.com.tiagocruz.ioasyschallenge.services;

import static java.util.Collections.emptySet;
import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiagocruz.ioasyschallenge.domain.entities.MovieEntity;
import br.com.tiagocruz.ioasyschallenge.domain.vos.MovieRequestVo;
import br.com.tiagocruz.ioasyschallenge.domain.vos.MovieResponseVo;
import br.com.tiagocruz.ioasyschallenge.exceptions.NotFoundException;
import br.com.tiagocruz.ioasyschallenge.repositories.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;
	private final ModelMapper mapper;

	@Autowired
	public MovieService(final MovieRepository movieRepository, final ModelMapper mapper) {

		this.movieRepository = movieRepository;
		this.mapper = mapper;
	}

	public MovieResponseVo createMovie(final MovieRequestVo movieRequestVo) {

		final MovieEntity movieEntity = mapper.map(movieRequestVo, MovieEntity.class);

		final MovieEntity savedMovieEntity = movieRepository.save(movieEntity);

		return mapper.map(savedMovieEntity, MovieResponseVo.class);
	}

	public MovieResponseVo getMovie(final Integer id) {

		final MovieEntity movieEntityFound = movieRepository.findById(id).orElseThrow(NotFoundException::movieNotFound);

		return mapper.map(movieEntityFound, MovieResponseVo.class);
	}

	public List<MovieResponseVo> getMovies(final String name, final String directorName, final String genre, final Set<String> actors) {

		final List<MovieEntity> moviesFound = movieRepository
				.findByNameOrDirectorNameOrGenreOrActorsInOrderByName(name, directorName, genre, ofNullable(actors).orElse(emptySet()));

		if (isEmpty(moviesFound)) {
			throw NotFoundException.movieNotFound();
		}

		return moviesFound.stream().map(movieEntity -> mapper.map(movieEntity, MovieResponseVo.class)).collect(Collectors.toList());
	}
}
