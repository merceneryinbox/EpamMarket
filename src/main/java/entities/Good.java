package entities;

import lombok.Data;

@Data
public class Good {
    private Integer id;
    private String name;
    private Integer amount;
    private String description;
}
