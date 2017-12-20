package dao;

import entities.Reserve;

import java.util.List;
import java.util.Optional;

public interface CartDAO {
    Optional<List<Reserve>> getReserveListByLogin(Integer login);

    Optional<Reserve> getReserveById(Integer reserveId);

    Optional<Reserve> getReserve(Integer userId, Integer goodId);

    void setAmountByLoginAndGoodId(Integer login, Integer goodId, Integer amount);
}
