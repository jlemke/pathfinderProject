package entity;

import javax.persistence.*;

/**
 * Created by Joe on 2/16/2017.
 */
@Entity
@Table(name = "reserved_users", schema = "pathfinderdb", catalog = "")
public class ReservedUser {

    private String username;
    private String password;
    private String email;
    private String activationCode;

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

    @Basic
    @Column(name = "activation_code", nullable = true, length = 40)
    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservedUser reservedUser = (ReservedUser) o;

        if (username != null ? !username.equals(reservedUser.username) : reservedUser.username != null) return false;
        if (password != null ? !password.equals(reservedUser.password) : reservedUser.password != null) return false;
        if (email != null ? !email.equals(reservedUser.email) : reservedUser.email != null) return false;
        if (activationCode != null ? !activationCode.equals(reservedUser.activationCode) : reservedUser.activationCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (activationCode != null ? activationCode.hashCode() : 0);
        return result;
    }

}
