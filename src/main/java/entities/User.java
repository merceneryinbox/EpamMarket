package entities;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String status;
}
