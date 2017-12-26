package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    private Integer id;
    private Integer userId;
    private Integer goodId;
    private Integer amount;
    private Timestamp reserveTime;

}
