package br.com.tiagocruz.ioasyschallenge.services;

import br.com.tiagocruz.ioasyschallenge.domain.entities.MovieEntity;
import br.com.tiagocruz.ioasyschallenge.domain.vos.MovieRequestVo;
import br.com.tiagocruz.ioasyschallenge.domain.vos.MovieResponseVo;
import br.com.tiagocruz.ioasyschallenge.exceptions.NotFoundException;
import br.com.tiagocruz.ioasyschallenge.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void createMovie_Should_CreateNewMovie_When_ValidMovieRequest() {

        //Given
        final MovieRequestVo movieRequestVo = new MovieRequestVo();
        movieRequestVo.setName("Star Wars");

        final MovieEntity convertedMovieEntity = new MovieEntity();
        convertedMovieEntity.setName("Star Wars");

        final MovieEntity savedMovieEntity = new MovieEntity();
        savedMovieEntity.setName("Star Wars");

        final MovieResponseVo returnedMovieResponseVo = new MovieResponseVo();
        returnedMovieResponseVo.setName("Star Wars");
        returnedMovieResponseVo.setId(2);

        when(mapper.map(movieRequestVo, MovieEntity.class)).thenReturn(convertedMovieEntity);

        when(movieRepository.save(convertedMovieEntity)).thenReturn(savedMovieEntity);

        when(mapper.map(savedMovieEntity, MovieResponseVo.class)).thenReturn(returnedMovieResponseVo);

        //When
        final MovieResponseVo result = movieService.createMovie(movieRequestVo);

        //Then
        assertThat(result.getName(), equalTo("Star Wars"));
        assertThat(result.getId(), is(2));

        verify(movieRepository).save(convertedMovieEntity);
        verify(mapper).map(movieRequestVo, MovieEntity.class);
        verify(mapper).map(savedMovieEntity, MovieResponseVo.class);
    }

    @Test
    void getMovies_Should_ThrowException_When_MoviesNotFound() {

        //Given
        final Set<String> actors;
        actors = new HashSet<String>();

        when(movieRepository
                .findByNameOrDirectorNameOrGenreOrActorsInOrderByName("Ioasys", "director", "adventure", actors))
                .thenReturn(emptyList());

        //When
        final NotFoundException thrown = assertThrows(NotFoundException.class, () -> movieService.getMovies("Ioasys", "director", "adventure", actors));

        //Then
        assertThat(thrown.getIssue().getCode(), is(13));
        assertThat(thrown.getIssue().getMessage(), equalTo("Movie not found"));

        verifyNoMoreInteractions(mapper, movieRepository);
    }

    @Test
    void getMovies_Should_ListUsers_When_MoviesFound() {

        //Given
        final Set<String> actors;
        actors = new HashSet<String>();

        when(movieRepository.findByNameOrDirectorNameOrGenreOrActorsInOrderByName("Ioasys", "director", "adventure", actors))
                .thenReturn(singletonList(new MovieEntity()));

        //When
        final List<MovieResponseVo> result = movieService.getMovies("Ioasys", "director", "adventure", actors);

        //Then
        assertThat(result, hasSize(1));

        verifyNoMoreInteractions(movieRepository);
    }
}