package br.com.tiagocruz.ioasyschallenge.domain.enums;

public enum ProfileEnum {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private final int code;

    private final String role;

    ProfileEnum(final int code, final String role) {

        this.code = code;
        this.role = role;
    }

    public int getCode() {

        return code;
    }

    public String getRole() {
        return role;
    }
}
