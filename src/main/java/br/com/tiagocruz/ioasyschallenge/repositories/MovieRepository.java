package br.com.tiagocruz.ioasyschallenge.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagocruz.ioasyschallenge.domain.entities.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

	List<MovieEntity> findByNameOrDirectorNameOrGenreOrActorsInOrderByName(String name, String directorName, String genre,
			Set<String> actors);
}
