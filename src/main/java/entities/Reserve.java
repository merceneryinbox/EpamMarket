package entities;

import lombok.Data;

@Data
public class Reserve {
    private Integer id;
    private String login;
    private Integer goodId;
    private Integer amount;
    private String reserveTime;
}
