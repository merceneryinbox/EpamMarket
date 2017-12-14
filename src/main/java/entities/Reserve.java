package entities;

import lombok.Data;

@Data
public class Reserve {
    private Integer id;
    private String login;
    private Integer good_id;
    private Integer amount;
    private String reserve_time;
}
