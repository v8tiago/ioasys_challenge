package br.com.tiagocruz.ioasyschallenge.controllers;

import br.com.tiagocruz.ioasyschallenge.domain.vos.UserRequestVo;
import br.com.tiagocruz.ioasyschallenge.domain.vos.UserResponseVo;
import br.com.tiagocruz.ioasyschallenge.helpers.constants.ApiConstants;
import br.com.tiagocruz.ioasyschallenge.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api("Users API")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "User Creation", notes = "Create an specific User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = ApiConstants.HTTP_CREATED),
            @ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
            @ApiResponse(code = 409, message = ApiConstants.HTTP_CONFLICT),
            @ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
            @ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
            @ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final UserRequestVo userRequestVo) {

        LOGGER.info("POST /users - UserName: {}", userRequestVo.getUserName());

        final UserResponseVo userResponseVo = userService.createUser(userRequestVo);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponseVo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "User Update", notes = "Update an specific User")
    @ApiResponses(value = {@ApiResponse(code = 202, message = ApiConstants.HTTP_ACCEPT_MESSAGE),
            @ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
            @ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
            @ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
            @ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping
    public ResponseEntity<UserResponseVo> update(@Valid @RequestBody final UserRequestVo userRequestVo) {

        LOGGER.info("PUT /users - UserName: {}", "user-2");
        final UserResponseVo updatedUser = userService.update(userRequestVo);
        return ResponseEntity.ok(updatedUser);
    }

    @ApiOperation(value = "User Deletion", notes = "Delete an specific User")
    @ApiResponses(value = {@ApiResponse(code = 202, message = ApiConstants.HTTP_ACCEPT_MESSAGE),
            @ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
            @ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
            @ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
            @ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> delete(@ApiParam(value = "id", required = true) @PathVariable final Integer id) {

        LOGGER.info("DELETE /users/{}", id);

        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "User List", notes = "Get a list of active Users order by name")
    @ApiResponses(value = {@ApiResponse(code = 204, message = ApiConstants.HTTP_NO_CONTENT),
            @ApiResponse(code = 400, message = ApiConstants.HTTP_BAD_REQUEST_MESSAGE),
            @ApiResponse(code = 403, message = ApiConstants.HTTP_FORBIDDEN_MESSAGE),
            @ApiResponse(code = 405, message = ApiConstants.HTTP_METHOD_NOT_ALLOWED_MESSAGE),
            @ApiResponse(code = 500, message = ApiConstants.HTTP_INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping
    public ResponseEntity<List<UserResponseVo>> get() {

        LOGGER.info("GET /users");

        final List<UserResponseVo> userResponseVos = userService.getUsers();

        return ResponseEntity.ok(userResponseVos);
    }
}
