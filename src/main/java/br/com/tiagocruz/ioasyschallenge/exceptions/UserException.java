package br.com.tiagocruz.ioasyschallenge.exceptions;

public class UserException extends GlobalException {

    private static final long serialVersionUID = -6446551207974112488L;

    protected UserException(final Issue issue) {
        super(issue);
    }

    public static UserException duplicateUserName(final String userName) {
        return new UserException(new Issue(IssueEnum.USERNAME_UNIQUE_VIOLATION, userName));
    }
}

