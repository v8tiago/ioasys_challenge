package br.com.tiagocruz.ioasyschallenge.controllers;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiagocruz.ioasyschallenge.domain.vos.MovieRequestVo;
import br.com.tiagocruz.ioasyschallenge.domain.vos.MovieResponseVo;
import br.com.tiagocruz.ioasyschallenge.helpers.constants.ApiConstants;
import br.com.tiagocruz.ioasyschallenge.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
@Api("Movies API")
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

	private final MovieService movieService;

	@Autowired
	public MovieController(final MovieService movieService) {

		this.movieService = movieService;
	}

	@ApiOperation(value = "Movie Creation", notes = "Create an specific Movie")
	@ApiResponses(value = { @ApiResponse(code = 201, message = ApiConstants.HTTP_CREATED),
			@ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
			@ApiResponse(code = 409, message = ApiConstants.HTTP_CONFLICT),
			@ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
			@ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
			@ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE) })
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody final MovieRequestVo movieRequestVo) {

		LOGGER.info("POST /movies - Name: {}", movieRequestVo.getName());

		final MovieResponseVo movieResponseVo = movieService.createMovie(movieRequestVo);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(movieResponseVo.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Movie information", notes = "Show the movie information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ApiConstants.HTTP_OK),
			@ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
			@ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
			@ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
			@ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE) })
	@GetMapping("/{id}")
	public ResponseEntity<MovieResponseVo> get(@ApiParam(value = "The Movie ID", required = true) @NotNull @PathVariable final Integer id) {

		LOGGER.info("GET /movies - Id: {}", id);

		final MovieResponseVo movieResponseVo = movieService.getMovie(id);

		return ResponseEntity.ok(movieResponseVo);
	}

	@ApiOperation(value = "Movie filter", notes = "Show the movie information by filters")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ApiConstants.HTTP_OK),
			@ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
			@ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
			@ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
			@ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE) })
	@GetMapping
	public ResponseEntity<List<MovieResponseVo>> get(
			@ApiParam("Movie Name") @RequestParam(required = false) final String name,
			@ApiParam("Director Name") @RequestParam(required = false) final String directorName,
			@ApiParam("Genre") @RequestParam(required = false) final String genre,
			@ApiParam("List of Actors") @RequestParam(required = false) final Set<String> actors) {

		LOGGER.info("GET /movies - Name: {}, Director: {}, Genre: {}, Actors: {}", name, directorName, genre, actors);

		final List<MovieResponseVo> movieResponseVos = movieService.getMovies(name, directorName, genre, actors);

		return ResponseEntity.ok(movieResponseVos);
	}
}
