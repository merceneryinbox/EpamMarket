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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reserve)) {
            return false;
        }
        Reserve other = (Reserve) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reserve[ id = " + id + " userId " + userId + " goodId " + goodId + " amount " + amount + " reserveTime " + reserveTime + " ] ";
    }
}
