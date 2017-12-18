package entities;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class Reserve {
    private Integer id;
    private String login;
    private Integer goodId;
    private Integer amount;
    private String reserveTime;
}
