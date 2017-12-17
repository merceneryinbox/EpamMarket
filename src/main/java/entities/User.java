package entities;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String status;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
