package entities;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class Good {
    private Integer id;
    private String name;
    private Integer amount;
    private String description;
}
