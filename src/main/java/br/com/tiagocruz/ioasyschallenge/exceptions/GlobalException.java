package br.com.tiagocruz.ioasyschallenge.exceptions;

public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = -1949970260779304121L;

	private final Issue issue;

	protected GlobalException(final Issue issue) {

		this.issue = issue;
	}

	protected GlobalException(final Issue issue, final Throwable cause) {

		super(issue.getMessage(), cause);
		this.issue = issue;
	}

	public Issue getIssue() {

		return issue;
	}
}
