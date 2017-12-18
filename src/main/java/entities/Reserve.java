package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    private Integer id;
    private String login;
    private Integer goodId;
    private Integer amount;
    private Timestamp reserveTime;

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", goodId=" + goodId +
                ", amount=" + amount +
                ", reserveTime='" + reserveTime + '\'' +
                '}';
    }
}
