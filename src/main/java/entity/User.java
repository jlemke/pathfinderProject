package entity;

import entity.sheet.Sheet;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "users", schema = "pathfinderdb", catalog = "")
public class User {
    private String username;
    private String password;
    private String email;
    private Collection<Sheet> sheetsByUsername;
    private Collection<UserRoles> userRolesByUsername;

    @Id
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByOwner")
    public Collection<Sheet> getSheetsByUsername() {
        return sheetsByUsername;
    }

    public void setSheetsByUsername(Collection<Sheet> sheetsByUsername) {
        this.sheetsByUsername = sheetsByUsername;
    }

    @OneToMany(mappedBy = "usersByUsername")
    public Collection<UserRoles> getUserRolesByUsername() {
        return userRolesByUsername;
    }

    public void setUserRolesByUsername(Collection<UserRoles> userRolesByUsername) {
        this.userRolesByUsername = userRolesByUsername;
    }
}
