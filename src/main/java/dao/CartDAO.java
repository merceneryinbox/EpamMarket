package dao;

import entities.Reserve;

import java.util.List;
import java.util.Optional;

public interface CartDAO {
    Optional<List<Reserve>> getReserveListByLogin(Integer login);
    Optional<Reserve> getReserve(Integer userId, Integer goodId);
    void setAmountByLoginAndGoodId(Integer userId, Integer goodId, Integer amount);
}
