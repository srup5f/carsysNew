package com.jiker.carsys;

import org.example.CarsysApplication;
import org.example.bean.CarAccessories;
import org.example.service.CarAccessoriesService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CarAccessoriesServiceTest {
    CarAccessoriesService service=null;
    @Before
    public void setUp() throws Exception {
        service=new CarAccessoriesService();
    }
    @After
    public void tearDown() throws Exception {
        service=null;
    }
    @Test
    public void insert() throws IllegalAccessException, SQLException, InstantiationException, IOException {
        service=new CarAccessoriesService();
        int size1=service.query(null,null).size();
        CarAccessories ca1=new CarAccessories(21,"雪佛兰","发动机",true,  5000.0f);
        CarAccessories ca2=new CarAccessories(22,"雪佛兰","轮胎",true,2000.0f);
        List<CarAccessories> carAccessoriesList=new ArrayList<CarAccessories>();
        carAccessoriesList.add(ca1);
        carAccessoriesList.add(ca2);
        service.insert(carAccessoriesList);
        int size2=service.query(null,null).size();
        assertEquals(size1+2,size2);
    }
    @Test
    public void delete() throws IllegalAccessException, SQLException, InstantiationException, IOException {
        service=new CarAccessoriesService();
        //删除 轮胎无货
        String[] queryFields=new String[]{"accessoriesName","availability"};
        Object[] queryValues=new Object[]{"轮胎",false};
        service.delete(queryFields,queryValues);
    }
    @Test
    public void update() throws IllegalAccessException, SQLException, InstantiationException, IOException {
        service=new CarAccessoriesService();
        // 将价格小于等于1000的轮胎修改为无货
        String[] queryFields=new String[]{"accessoriesName","price"};
        Object[] queryValues=new Object[]{"轮胎",1000.0f};
        String[] upFields=new String[]{"availability"};
        Object[] upValues=new Object[]{false};
        service.update(upFields,upValues,queryFields,queryValues);
    }
    @Test
    public void query() throws IllegalAccessException, SQLException, InstantiationException, IOException {
        service=new CarAccessoriesService();
        //查询所有
        List<CarAccessories> cas=service.query(null,null);
        System.out.println(cas.size());
        //查询轮胎，有货，价格低于5000
        String[] queryFields=new String[]{"accessoriesName","availability","price"};
        Object[] queryValues=new Object[]{"轮胎",true,10000.0f};
        cas=service.query(queryFields,queryValues);
        System.out.println(cas.size());
    }
}
