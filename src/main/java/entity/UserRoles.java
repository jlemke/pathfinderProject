package entity;

import javax.persistence.*;

/**
 * Created by Joe on 12/3/2016.
 */
@Entity
@Table(name = "user_roles", schema = "pathfinderdb", catalog = "")
@IdClass(UserRolesPK.class)
public class UserRoles {
    private String username;
    private String roleName;
    private User usersByUsername;

    @Id
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "role_name", nullable = false, length = 15)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (username != null ? !username.equals(userRoles.username) : userRoles.username != null) return false;
        if (roleName != null ? !roleName.equals(userRoles.roleName) : userRoles.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    public User getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(User usersByUsername) {
        this.usersByUsername = usersByUsername;
    }
}
