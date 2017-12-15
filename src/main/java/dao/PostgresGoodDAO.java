package dao;

import entities.Good;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

public class PostgresGoodDAO implements GoodDAO {
    @Override
    public Optional<Good> getGoodByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public void addGood(Good good) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteGoodByName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public void updateGood(Good good) {
        throw new NotImplementedException();
    }
}
