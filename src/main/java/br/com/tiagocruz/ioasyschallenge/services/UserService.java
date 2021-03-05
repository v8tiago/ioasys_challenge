package br.com.tiagocruz.ioasyschallenge.services;

import br.com.tiagocruz.ioasyschallenge.config.WebSecurityConfig;
import br.com.tiagocruz.ioasyschallenge.domain.entities.UserEntity;
import br.com.tiagocruz.ioasyschallenge.domain.vos.UserRequestVo;
import br.com.tiagocruz.ioasyschallenge.domain.vos.UserResponseVo;
import br.com.tiagocruz.ioasyschallenge.exceptions.NotFoundException;
import br.com.tiagocruz.ioasyschallenge.exceptions.UserException;
import br.com.tiagocruz.ioasyschallenge.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    private WebSecurityConfig pe;

    @Autowired
    public UserService(final UserRepository userRepository, final ModelMapper mapper) {

        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserResponseVo createUser(final UserRequestVo userRequestVo) {

        final List<UserEntity> foundUsers = userRepository.findAllByUserNameAndIsDeletedIsFalse(userRequestVo.getUserName());

        if (isNotEmpty(foundUsers)) {
            throw UserException.duplicateUserName(userRequestVo.getUserName());
        }

        final UserEntity userEntity = mapper.map(userRequestVo, UserEntity.class);

        final UserEntity savedUserEntity = userRepository.save(userEntity);

        return mapper.map(savedUserEntity, UserResponseVo.class);
    }

    public UserResponseVo update(final UserRequestVo userRequestVo) {

        final List<UserEntity> allUserEntity = userRepository.findAllByUserName(
                userRequestVo.getUserName());

        if (isNotEmpty(allUserEntity)) {
            throw UserException.duplicateUserName(userRequestVo.getUserName());
        }

        final UserEntity userEntity = mapper.map(userRequestVo, UserEntity.class);

        final UserEntity updateUserEntity = userRepository.save(userEntity);

        return mapper.map(updateUserEntity, UserResponseVo.class);
    }

    public void deleteUser(final Integer id) {

        final UserEntity userEntity = userRepository.findById(id).orElseThrow(NotFoundException::userNotFound);

        userEntity.setIsDeleted(Boolean.TRUE);

        userRepository.save(userEntity);
    }

    public List<UserResponseVo> getUsers() {

        final List<UserEntity> usersFound = userRepository.findAllByIsAdminFalseAndIsDeletedFalseOrderByUserName();

        if (isEmpty(usersFound)) {
            throw NotFoundException.userNotFound();
        }
        return usersFound.stream().map(usersEntity -> mapper.map(usersEntity, UserResponseVo.class)).collect(Collectors.toList());
    }
}
