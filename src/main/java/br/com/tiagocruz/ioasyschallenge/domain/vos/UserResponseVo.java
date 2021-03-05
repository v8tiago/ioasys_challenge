package br.com.tiagocruz.ioasyschallenge.domain.vos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel("UserResponse")
public class UserResponseVo {


    @NotNull
    @ApiModelProperty(value = "The ID of the user")
    private Integer id;

    @ApiModelProperty("The userName chosen by user")
    private String userName;

    @ApiModelProperty("Indicate if user is an administrator")
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

    public void setIsAdmin(final Boolean isAdmin) {

        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserResponseVo that = (UserResponseVo) o;
        return userName.equals(that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName);
    }
}
