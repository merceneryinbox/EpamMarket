package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Comparable {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String status;

    public static User testUserForName(String name) {
        return new User(0, name, name.hashCode() + "", name + "@email.com", "+7" + (+name
                .hashCode() + "0000000000").substring(0, 10), "Active");
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return this.getLogin().compareTo(user.getLogin());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Good)) {
            return false;
        }
        Good other = (Good) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ login " + login + " status " + status + " ] ";
    }
}
