package dao;

import entities.Good;


import java.util.Optional;

public class PostgresGoodDAO implements GoodDAO {
    @Override
    public Optional<Good> getGoodByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addGood(Good good) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGoodByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateGood(Good good) {
        throw new UnsupportedOperationException();
    }
}
