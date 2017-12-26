package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Good implements Comparable {
    private Integer id;
    private String name;
    private Double price;
    private Integer amount;
    private String description;

    @Override
    public int compareTo(Object o) {
        Good good = (Good) o;
        return this.getName().compareTo(good.getName());
    }
}
