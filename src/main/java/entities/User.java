package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
}
