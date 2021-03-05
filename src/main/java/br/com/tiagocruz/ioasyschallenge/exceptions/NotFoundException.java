package br.com.tiagocruz.ioasyschallenge.exceptions;

public class NotFoundException extends GlobalException {

	private static final long serialVersionUID = -6446551207974112488L;

	protected NotFoundException(final Issue issue) {

		super(issue);
	}

	public static NotFoundException userNotFound() {

		return new NotFoundException(new Issue(IssueEnum.USER_NOT_FOUND));
	}

	public static NotFoundException movieNotFound() {

		return new NotFoundException(new Issue(IssueEnum.MOVIE_NOT_FOUND));
	}
}
