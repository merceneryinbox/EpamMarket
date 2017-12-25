package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class User implements Comparable {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String status;


    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return this.getLogin().compareTo(user.getLogin());
    }

}
