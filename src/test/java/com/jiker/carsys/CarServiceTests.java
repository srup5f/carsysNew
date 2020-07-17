package com.jiker.carsys;

import org.example.bean.Car;
import org.example.service.CarService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CarServiceTests {
	CarService service=null;

	@Before
	public void setUp() throws Exception {

		service=new CarService();
		System.out.println("before 执行");
	}
	@After
	public void tearDown() throws Exception {
		service=null;
	}
	@Test
	public void insert() throws IllegalAccessException, SQLException, InstantiationException, IOException {
		service=new CarService();
		int size1=service.query(null,null).size();
		Car car1=new Car(9,"雪佛兰","SUV",120005.0f);
		Car car2=new Car(10,"奥迪","轿车",500000.0f);
		List<Car> cars=new ArrayList<Car>();
		cars.add(car1);
		cars.add(car2);
		service.insert(cars);
		int size2=service.query(null,null).size();
		assertEquals(size1+2,size2);
	}


	@Test
	public void delete() throws IllegalAccessException, SQLException, InstantiationException, IOException {
		service=new CarService();
		//删除宝马，SUV,价格低于500000
		String[] queryFields=new String[]{"brand","type","price"};
		Object[] queryValues=new Object[]{"宝马","SUV",500000.0f};
		service.delete(queryFields,queryValues);
	}
	@Test
	public void update() throws IllegalAccessException, SQLException, InstantiationException, IOException {
		service=new CarService();
		// 更新SUV 的价格
		String[] upFields=new String[]{"price"};
		Object[] upValues=new Object[]{600000.0f};
		String[] queryFields=new String[]{"type"};
		Object[] queryValues=new Object[]{"SUV"};
		service.update(upFields,upValues,queryFields,queryValues);
	}
	@Test
	public void query() throws IllegalAccessException, SQLException, InstantiationException, IOException {
		service=new CarService();
		//查询所有
		List<Car> cars=service.query(null,null);
		System.out.println(cars.size());
		//查询所有奔驰车
		String[] queryFields=new String[]{"brand"};
		Object[] queryValues=new Object[]{"奔驰"};
		cars=service.query(queryFields,queryValues);
		System.out.println(cars.size());
		//查询奔驰，SUV
		queryFields=new String[]{"brand","type"};
		queryValues=new Object[]{"奔驰","SUV"};
		cars=service.query(queryFields,queryValues);
		System.out.println(cars.size());
		//查询轿车，价格小于200000.0
		queryFields=new String[]{"type","price"};
		queryValues=new Object[]{"轿车",200000.0f};
		cars=service.query(queryFields,queryValues);
		System.out.println(cars.size());
	}
}
