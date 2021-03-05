package br.com.tiagocruz.ioasyschallenge.domain.entities;

import br.com.tiagocruz.ioasyschallenge.domain.enums.ProfileEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -3908376807059220089L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @ElementCollection()
    @CollectionTable(name = "USER_PROFILES")
    private Set<Integer> profiles;

    public Set<Integer> getProfiles() {
        return profiles;
    }

    public void addProfile(final ProfileEnum perfil) {
        profiles.add(perfil.getCode());
    }

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

    public Boolean getIsDeleted() {

        return isDeleted;
    }

    public void setIsDeleted(final Boolean deleted) {

        isDeleted = deleted;
    }
}
