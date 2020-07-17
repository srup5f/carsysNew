package org.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.bean.Car;
import org.example.dao.ICarDao;
import org.example.util.ConvertClass;
import org.example.util.DBManager;

public class CarService {
    ICarDao ICarDao;

    public CarService() throws IOException {
        String resource = "map/MybatisConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        DBManager dbManager = new DBManager();
        dbManager.conn_init("mysql","jdbc:mysql://localhost:3306/carsys?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
        "root","123456" );
        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        ICarDao = sqlSession.getMapper(ICarDao.class);
    }

    public List<Car> query(String[] queryFields, Object[] queryValues) throws IllegalAccessException, SQLException, InstantiationException {
        if(queryFields==null || queryValues==null){
            Car car = new Car();
            List<Car> cars = ICarDao.query(car);
            return cars;
        }
        Car car = ConvertClass.packClassObj(Car.class, queryFields, queryValues);
        List<Car> cars = ICarDao.query(car);
        return cars;
    }

    public void insert(List<Car> cars) {
        ICarDao.insert(cars);
    }

    public void delete(String[] queryFields, Object[] queryValues) throws IllegalAccessException, SQLException, InstantiationException {
        Car car = ConvertClass.packClassObj(Car.class, queryFields, queryValues);
        ICarDao.delete(car);
    }

    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues) throws IllegalAccessException, SQLException, InstantiationException {
        Car querycar = ConvertClass.packClassObj(Car.class, queryFields, queryValues);
        Car updatecar=ConvertClass.packClassObj(Car.class, upFields, upValues);
        ICarDao.update(querycar,updatecar);


    }
}
