package br.com.tiagocruz.ioasyschallenge.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.google.common.base.Joiner;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected Issue processExceptions(final Exception e) {

		LOGGER.error(e.getMessage(), e);

		return new Issue(IssueEnum.UNEXPECTED_ERROR, Collections.singletonList(e.getLocalizedMessage()));
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, value = HttpStatus.METHOD_NOT_ALLOWED)
	protected Issue processHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e,
			final WebRequest request) {

		LOGGER.error(e.getMessage(), e);

		return new Issue(IssueEnum.METHOD_NOT_ALLOWED, e.getMethod(),
				Joiner.on(", ").join(Objects.requireNonNull(e.getSupportedHttpMethods())));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected Issue handleMethodArgumentNotValid(final MethodArgumentNotValidException e) {

		LOGGER.error(e.getMessage(), e);

		final List<String> errors = new ArrayList<>();
		for (final FieldError error : e.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : e.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		return new Issue(IssueEnum.BAD_REQUEST, errors);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected Issue hadleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {

		LOGGER.error(e.getMessage(), e);

		return new Issue(IssueEnum.JSON_DESERIALIZE_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public Issue constraintViolationException(final DataIntegrityViolationException e) {

		LOGGER.error(e.getMessage(), e);

		return new Issue(IssueEnum.DATA_INTEGRITY_VIOLATION, e.getMostSpecificCause().getMessage());
	}

	@ExceptionHandler(UserException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public Issue processUserException(final UserException e) {

		LOGGER.error(e.getMessage(), e);

		return e.getIssue();
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Issue processNotFoundException(final NotFoundException e) {

		LOGGER.error(e.getMessage(), e);

		return e.getIssue();
	}

}
