package dao;

import entities.Reserve;

import java.util.List;
import java.util.Optional;

public interface CartDAO {
    Optional<List<Reserve>> getAllReserves();

    Optional<List<Reserve>> getReserveListByUserId(Integer login);

    void setAmountByLoginAndGoodId(Integer userId, Integer goodId, Integer amount);

    Optional<Reserve> getReserveById(Integer reserveId);

    Optional<Reserve> getReserve(Integer userId, Integer goodId);

    void deleteReserve(Integer userId, Integer goodId);

    void deleteAllReservesByUserId(Integer userId);
}