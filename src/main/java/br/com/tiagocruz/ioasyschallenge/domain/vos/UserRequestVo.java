package br.com.tiagocruz.ioasyschallenge.domain.vos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@ApiModel("UserRequest")
public class UserRequestVo implements Serializable {

    private static final long serialVersionUID = 4881022215393231658L;

    @NotNull
    @ApiModelProperty(value = "The internal id of the user", required = true)
    private Integer id;

    @NotBlank
    @ApiModelProperty(value = "The userName chosen by user", required = true)
    private String userName;

    @NotBlank
    @ApiModelProperty(value = "The password chosen by user", required = true)
    private String password;

    @NotNull
    @ApiModelProperty(value = "Indicate if user is an administrator", required = true)
    private Boolean isAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(final String userName) {

        this.userName = userName;
    }

    public Boolean getIsAdmin() {

        return isAdmin;
    }

    public void setIsAdmin(final Boolean admin) {

        isAdmin = admin;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserRequestVo that = (UserRequestVo) o;
        return userName.equals(that.userName) && isAdmin.equals(that.isAdmin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, isAdmin);
    }
}
