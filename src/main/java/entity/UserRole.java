package entity;

import javax.persistence.*;

/**
 * Created by Joe on 12/5/2016.
 */
@Entity
@Table(name = "user_roles", schema = "pathfinderdb", catalog = "")
@IdClass(UserRolePK.class)
public class UserRole {
    private String username;
    private String roleName;
    private User user;

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

        UserRole userRole = (UserRole) o;

        if (username != null ? !username.equals(userRole.username) : userRole.username != null) return false;
        if (roleName != null ? !roleName.equals(userRole.roleName) : userRole.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "username", nullable = false, insertable = false, updatable = false)
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}
