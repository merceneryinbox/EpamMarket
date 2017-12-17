package dao;

import entities.Good;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GoodDAO {
    Optional<Good> getGoodByName(String name);
    void addGood(Good good);
    void deleteGoodByName(String name);
    void updateGood(Good good);
}
