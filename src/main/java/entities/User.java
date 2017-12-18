package entities;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String status;
}
