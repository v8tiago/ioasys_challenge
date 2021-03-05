package br.com.tiagocruz.ioasyschallenge.domain.vos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private final String username;
	private final String password;

	@JsonCreator
	public JwtRequest(@JsonProperty("username") final String username, @JsonProperty("password") final String password) {

		this.username = username;
		this.password = password;
	}

	public String getUsername() {

		return username;
	}

	public String getPassword() {

		return password;
	}
}