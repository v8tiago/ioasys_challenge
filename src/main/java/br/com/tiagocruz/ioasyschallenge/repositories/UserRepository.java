package br.com.tiagocruz.ioasyschallenge.repositories;

import br.com.tiagocruz.ioasyschallenge.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findAllByUserName(String userName);

    List<UserEntity> findAllByUserNameAndIsDeletedIsFalse(String userName);

    List<UserEntity> findAllByIsAdminFalseAndIsDeletedFalseOrderByUserName();
}
