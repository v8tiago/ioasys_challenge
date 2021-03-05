package br.com.tiagocruz.ioasyschallenge.exceptions;

import static java.util.Objects.isNull;

import java.util.IllegalFormatException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum IssueEnum {
	UNEXPECTED_ERROR(6, "Unexpected error. Please contact system administrator."),
	METHOD_NOT_ALLOWED(7, "%s method is not supported for this request. Supported methods are [%s]"),
	BAD_REQUEST(8, "Malformed Request"),
	JSON_DESERIALIZE_ERROR(9, "Can not deserialize JSON."),
	DATA_INTEGRITY_VIOLATION(10, "Data integrity violation. [%s]"),
	USERNAME_UNIQUE_VIOLATION(11, "User %s already exists"),
	USER_NOT_FOUND(12, "User not found"),
	MOVIE_NOT_FOUND(13, "Movie not found");

	// not static because ENUMS are initialized before static fields by JVM
	private final Logger logger = LogManager.getLogger(IssueEnum.class);
	private final int code;
	private final String message;

	IssueEnum(final int code, final String message) {

		this.code = code;
		this.message = message;
	}

	public int getCode() {

		return code;
	}

	public String getFormattedMessage(final Object... args) {

		if (isNull(message)) {
			return "";
		}

		try {
			return String.format(message, args);
		} catch (final IllegalFormatException e) {
			logger.warn(e.getMessage(), e);
			return message.replace("%s", "");
		}
	}
}
