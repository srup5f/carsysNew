package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.bean.Car;

import java.util.List;

public interface ICarDao extends Mapper{

    void insert(List<Car> cars);

    void delete(@Param("c") Car car);

    void update(@Param("q") Car query,@Param("u") Car up);

    List<Car> query(@Param("c") Car car);

}
