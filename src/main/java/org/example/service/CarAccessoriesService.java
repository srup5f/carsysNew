package org.example.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.bean.Car;
import org.example.bean.CarAccessories;
import org.example.dao.ICarAccessoriesDao;
import org.example.util.ConvertClass;
import org.example.util.DBManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class CarAccessoriesService {
    ICarAccessoriesDao iCarAccessoriesDao;
         public CarAccessoriesService() throws IOException {
             DBManager dbManager = new DBManager();
             dbManager.conn_init("mysql","jdbc:postgresql://localhost:5432/carsys",
                     "postgres","123456" );
            String resource = "map/MybatisConfig.xml";
            // 得到配置文件流
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 创建会话工厂，传入mybatis的配置文件信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //创建UserMapper对象，mybatis自动生成mapper代理对象
             iCarAccessoriesDao = sqlSession.getMapper(ICarAccessoriesDao.class);
        }


    public void insert(List<CarAccessories> carAccessoriesList) {
        iCarAccessoriesDao.insert(carAccessoriesList);
    }

    public void delete(String[] queryFields, Object[] queryValues) throws IllegalAccessException, SQLException, InstantiationException {
        CarAccessories carAccessories = ConvertClass.packClassObj(CarAccessories.class, queryFields, queryValues);
        iCarAccessoriesDao.delete(carAccessories);
    }

    public void update(String[] upFields, Object[] upValues, String[] queryFields, Object[] queryValues) throws IllegalAccessException, SQLException, InstantiationException {

        if(queryFields!=null && queryValues!=null &&queryFields!=null &&queryValues!=null) {
            CarAccessories querycarAccessories = ConvertClass.packClassObj(CarAccessories.class, queryFields, queryValues);
            CarAccessories updatecarAccessories = ConvertClass.packClassObj(CarAccessories.class, upFields, upValues);
            iCarAccessoriesDao.update(querycarAccessories, updatecarAccessories);
        }
    }

    public List<CarAccessories> query(String[] queryFields, Object[] queryValues) throws IllegalAccessException, SQLException, InstantiationException {
        if(queryFields==null || queryValues==null){
            CarAccessories carAccessories = new CarAccessories();
            List<CarAccessories> accessoriesList = iCarAccessoriesDao.query(carAccessories);
            return accessoriesList;
        }
        CarAccessories querycarAccessories = ConvertClass.packClassObj(CarAccessories.class, queryFields, queryValues);
        List<CarAccessories> carAccessories = iCarAccessoriesDao.query(querycarAccessories);
        return carAccessories;

    }
}
