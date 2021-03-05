package br.com.tiagocruz.ioasyschallenge.services;

import br.com.tiagocruz.ioasyschallenge.domain.entities.UserEntity;
import br.com.tiagocruz.ioasyschallenge.domain.vos.UserRequestVo;
import br.com.tiagocruz.ioasyschallenge.domain.vos.UserResponseVo;
import br.com.tiagocruz.ioasyschallenge.exceptions.NotFoundException;
import br.com.tiagocruz.ioasyschallenge.exceptions.UserException;
import br.com.tiagocruz.ioasyschallenge.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void createUser_Should_CreateNewUser_When_ValidUserRequest() {

        //Given
        final UserRequestVo userRequestVo = new UserRequestVo();
        userRequestVo.setUserName("Tiago");

        final UserEntity convertedUserEntity = new UserEntity();
        convertedUserEntity.setUserName("Tiago");

        final UserEntity savedUserEntity = new UserEntity();
        savedUserEntity.setUserName("Tiago");

        final UserResponseVo returnedUserResponseVo = new UserResponseVo();
        returnedUserResponseVo.setUserName("Tiago");
        returnedUserResponseVo.setId(2);

        when(userRepository.findAllByUserNameAndIsDeletedIsFalse(userRequestVo.getUserName()))
                .thenReturn(emptyList());

        when(mapper.map(userRequestVo, UserEntity.class)).thenReturn(convertedUserEntity);

        when(userRepository.save(convertedUserEntity)).thenReturn(savedUserEntity);

        when(mapper.map(savedUserEntity, UserResponseVo.class)).thenReturn(returnedUserResponseVo);

        //When
        final UserResponseVo result = userService.createUser(userRequestVo);

        //Then
        assertThat(result.getUserName(), equalTo("Tiago"));
        assertThat(result.getId(), is(2));

        verify(userRepository).findAllByUserNameAndIsDeletedIsFalse("Tiago");
        verify(userRepository).save(convertedUserEntity);
        verify(mapper).map(userRequestVo, UserEntity.class);
        verify(mapper).map(savedUserEntity, UserResponseVo.class);
    }

    @Test
    void createUser_Should_ThrowException_When_UserNotFound() {

        //Given
        final UserRequestVo userRequestVo = new UserRequestVo();
        userRequestVo.setUserName("Tiago");

        when(userRepository.findAllByUserNameAndIsDeletedIsFalse(userRequestVo.getUserName()))
                .thenReturn(singletonList(new UserEntity()));

        //When
        final UserException thrown = assertThrows(UserException.class, () -> userService.createUser(userRequestVo));

        //Then
        assertThat(thrown.getIssue().getCode(), is(11));
        assertThat(thrown.getIssue().getMessage(), equalTo("User Tiago already exists"));

        verifyNoMoreInteractions(mapper, userRepository);
    }

    @Test
    void getUsers_Should_ThrowException_When_UsersNotFound() {

        //Given
        when(userRepository.findAllByIsAdminFalseAndIsDeletedFalseOrderByUserName())
                .thenReturn(emptyList());

        //When
        final NotFoundException thrown = assertThrows(NotFoundException.class, () -> userService.getUsers());

        //Then
        assertThat(thrown.getIssue().getCode(), is(12));
        assertThat(thrown.getIssue().getMessage(), equalTo("User not found"));

        verifyNoMoreInteractions(mapper, userRepository);
    }

    @Test
    void getUsers_Should_ListUsers_When_UsersFound() {

        //Given
        when(userRepository.findAllByIsAdminFalseAndIsDeletedFalseOrderByUserName())
                .thenReturn(singletonList(new UserEntity()));

        //When
        final List<UserResponseVo> result = userService.getUsers();

        //Then
        assertThat(result, hasSize(1));

        verifyNoMoreInteractions(userRepository);
    }


}