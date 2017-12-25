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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Good)) {
            return false;
        }
        Good other = (Good) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Good[ id = " + id + " name " + name + " price " + price + " amount " + amount + " ] ";
    }
}
