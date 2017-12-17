package entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Good {
    private Integer id;
    private String name;
    private Integer amount;
    private String description;
}
