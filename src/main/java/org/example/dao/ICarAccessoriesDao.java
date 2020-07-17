package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.bean.CarAccessories;

import java.util.List;

public interface ICarAccessoriesDao extends Mapper {

    void insert(List<CarAccessories> cas);

    void delete(@Param("ca") CarAccessories carAccessories);

    void update(@Param("q") CarAccessories query,@Param("u") CarAccessories up);


    List<CarAccessories> query(@Param("ca") CarAccessories carAccessories);
}
